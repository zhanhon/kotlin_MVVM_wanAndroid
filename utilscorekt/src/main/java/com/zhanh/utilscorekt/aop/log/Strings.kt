package com.zhanh.utilscorekt.aop.log

import java.util.*
import kotlin.collections.HashSet

internal class Strings private constructor() {
    companion object {
        fun toString(obj: Any?): String {
            if (obj == null) {
                return "null"
            }
            if (obj is CharSequence) {
                return '"'.toString() + printableToString(obj.toString()) + '"'
            }
            val cls: Class<*> = obj.javaClass
            if (Byte::class.java == cls) {
                return byteToString(obj as Byte?)
            }
            return if (cls.isArray) {
                arrayToString(cls.componentType, obj)
            } else obj.toString()
        }

        private fun printableToString(string: String): String {
            val length = string.length
            val builder = StringBuilder(length)
            var i = 0
            while (i < length) {
                val codePoint = string.codePointAt(i)
                when (Character.getType(codePoint)) {
                    Character.CONTROL.toInt(),
                    Character.FORMAT.toInt(),
                    Character.PRIVATE_USE.toInt(),
                    Character.SURROGATE.toInt(),
                    Character.UNASSIGNED.toInt() -> when (codePoint) {
                        '\n'.toInt() -> builder.append("\\n")
                        '\r'.toInt() -> builder.append("\\r")
                        '\t'.toInt() -> builder.append("\\t")
//                        '\f'.toInt() -> builder.append("\\f")
                        '\b'.toInt() -> builder.append("\\b")
                        else -> builder.append("\\u")
                            .append(String.format("%04x", codePoint).toUpperCase(Locale.US))
                    }
                    else -> builder.append(Character.toChars(codePoint))
                }
                i += Character.charCount(codePoint)
            }
            return builder.toString()
        }

        private fun arrayToString(cls: Class<*>?, obj: Any): String {
            if (Byte::class.javaPrimitiveType == cls) {
                return byteArrayToString(obj as ByteArray)
            }
            if (Short::class.javaPrimitiveType == cls) {
                return Arrays.toString(obj as ShortArray)
            }
            if (Char::class.javaPrimitiveType == cls) {
                return Arrays.toString(obj as CharArray)
            }
            if (Int::class.javaPrimitiveType == cls) {
                return Arrays.toString(obj as IntArray)
            }
            if (Long::class.javaPrimitiveType == cls) {
                return Arrays.toString(obj as LongArray)
            }
            if (Float::class.javaPrimitiveType == cls) {
                return Arrays.toString(obj as FloatArray)
            }
            if (Double::class.javaPrimitiveType == cls) {
                return Arrays.toString(obj as DoubleArray)
            }
            return if (Boolean::class.javaPrimitiveType == cls) {
                Arrays.toString(obj as BooleanArray)
            } else arrayToString(obj as Array<Any>)
        }

        /** A more human-friendly version of Arrays#toString(byte[]) that uses hex representation.  */
        private fun byteArrayToString(bytes: ByteArray): String {
            val builder = StringBuilder("[")
            for (i in bytes.indices) {
                if (i > 0) {
                    builder.append(", ")
                }
                builder.append(byteToString(bytes[i]))
            }
            return builder.append(']').toString()
        }

        private fun byteToString(b: Byte?): String {
            return if (b == null) {
                "null"
            } else "0x" + String.format("%02x", b).toUpperCase(Locale.US)
        }

        private fun arrayToString(array: Array<Any>): String {
            val buf = StringBuilder()
            arrayToString(array, buf, HashSet())
            return buf.toString()
        }

        private fun arrayToString(
            array: Array<Any>?,
            builder: StringBuilder,
            seen: MutableSet<Array<Any>>
        ) {
            if (array == null) {
                builder.append("null")
                return
            }
            seen.add(array)
            builder.append('[')
            for (i in array.indices) {
                if (i > 0) {
                    builder.append(", ")
                }
                val element = array[i]
                if (element == null) {
                    builder.append("null")
                } else {
                    val elementClass: Class<*> = element.javaClass
                    if (elementClass.isArray && elementClass.componentType == Any::class.java) {
                        val arrayElement =
                            element as Array<Any>
                        if (seen.contains(arrayElement)) {
                            builder.append("[...]")
                        } else {
                            arrayToString(arrayElement, builder, seen)
                        }
                    } else {
                        builder.append(toString(element))
                    }
                }
            }
            builder.append(']')
            seen.remove(array)
        }
    }

    init {
        throw AssertionError("No instances.")
    }
}