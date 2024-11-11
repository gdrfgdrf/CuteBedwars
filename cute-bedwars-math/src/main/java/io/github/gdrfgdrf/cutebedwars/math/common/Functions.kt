package io.github.gdrfgdrf.cutebedwars.math.common

class Functions<T> {
    var plusByte: (T.(Byte) -> Number)? = null
    var plusDouble: (T.(Double) -> Double)? = null
    var plusFloat: (T.(Float) -> Number)? = null
    var plusInt: (T.(Int) -> Number)? = null
    var plusLong: (T.(Long) -> Number)? = null
    var plusShort: (T.(Short) -> Number)? = null
    var unaryPlus: (T.() -> Number)? = null

    var minusByte: (T.(Byte) -> Number)? = null
    var minusDouble: (T.(Double) -> Number)? = null
    var minusFloat: (T.(Float) -> Number)? = null
    var minusInt: (T.(Int) -> Number)? = null
    var minusLong: (T.(Long) -> Number)? = null
    var minusShort: (T.(Short) -> Number)? = null
    var unaryMinus: (T.() -> Number)? = null

    var timesByte: (T.(Byte) -> Number)? = null
    var timesDouble: (T.(Double) -> Number)? = null
    var timesFloat: (T.(Float) -> Number)? = null
    var timesInt: (T.(Int) -> Number)? = null
    var timesLong: (T.(Long) -> Number)? = null
    var timesShort: (T.(Short) -> Number)? = null

    var divByte: (T.(Byte) -> Number)? = null
    var divDouble: (T.(Double) -> Number)? = null
    var divFloat: (T.(Float) -> Number)? = null
    var divInt: (T.(Int) -> Number)? = null
    var divLong: (T.(Long) -> Number)? = null
    var divShort: (T.(Short) -> Number)? = null

    var powDouble: (T.(Double) -> Number)? = null
    var powFloat: (T.(Float) -> Number)? = null
    var powInt: (T.(Int) -> Number)? = null

    var compareToByte: (T.(Byte) -> Int)? = null
    var compareToDouble: (T.(Double) -> Int)? = null
    var compareToFloat: (T.(Float) -> Int)? = null
    var compareToInt: (T.(Int) -> Int)? = null
    var compareToLong: (T.(Long) -> Int)? = null
    var compareToShort: (T.(Short) -> Int)? = null

    fun type(number: Number): Type {
        when (number.javaClass.simpleName) {
            "Byte" -> return Type.BYTE
            "Double" -> return Type.DOUBLE
            "Float" -> return Type.FLOAT
            "Integer" -> return Type.INT
            "Long" -> return Type.LONG
            "Short" -> return Type.SHORT
        }
        throw UnsupportedOperationException()
    }

    fun plus(number: Number, number2: Number): Number {
        val t = number as T
        return when (type(number2)) {
            Type.BYTE -> plusByte!!(t, number2 as Byte)
            Type.DOUBLE -> plusDouble!!(t, number2 as Double)
            Type.FLOAT -> plusFloat!!(t, number2 as Float)
            Type.INT -> plusInt!!(t, number2 as Int)
            Type.LONG -> plusLong!!(t, number2 as Long)
            Type.SHORT -> plusShort!!(t, number2 as Short)
        }
    }

    fun unaryPlus(number: Number): Number {
        val t = number as T
        return when (type(number)) {
            Type.BYTE -> unaryPlus!!(t)
            Type.DOUBLE -> unaryPlus!!(t)
            Type.FLOAT -> unaryPlus!!(t)
            Type.INT -> unaryPlus!!(t)
            Type.LONG -> unaryPlus!!(t)
            Type.SHORT -> unaryPlus!!(t)
        }
    }

    fun minus(number: Number, number2: Number): Number {
        val t = number as T
        return when (type(number2)) {
            Type.BYTE -> minusByte!!(t, number2 as Byte)
            Type.DOUBLE -> minusDouble!!(t, number2 as Double)
            Type.FLOAT -> minusFloat!!(t, number2 as Float)
            Type.INT -> minusInt!!(t, number2 as Int)
            Type.LONG -> minusLong!!(t, number2 as Long)
            Type.SHORT -> minusShort!!(t, number2 as Short)
        }
    }

    fun unaryMinus(number: Number): Number {
        val t = number as T
        return when (type(number)) {
            Type.BYTE -> unaryMinus!!(t)
            Type.DOUBLE -> unaryMinus!!(t)
            Type.FLOAT -> unaryMinus!!(t)
            Type.INT -> unaryMinus!!(t)
            Type.LONG -> unaryMinus!!(t)
            Type.SHORT -> unaryMinus!!(t)
        }
    }

    fun times(number: Number, number2: Number): Number {
        val t = number as T
        return when (type(number2)) {
            Type.BYTE -> timesByte!!(t, number2 as Byte)
            Type.DOUBLE -> timesDouble!!(t, number2 as Double)
            Type.FLOAT -> timesFloat!!(t, number2 as Float)
            Type.INT -> timesInt!!(t, number2 as Int)
            Type.LONG -> timesLong!!(t, number2 as Long)
            Type.SHORT -> timesShort!!(t, number2 as Short)
        }
    }

    fun div(number: Number, number2: Number): Number {
        val t = number as T
        return when (type(number2)) {
            Type.BYTE -> divByte!!(t, number2 as Byte)
            Type.DOUBLE -> divDouble!!(t, number2 as Double)
            Type.FLOAT -> divFloat!!(t, number2 as Float)
            Type.INT -> divInt!!(t, number2 as Int)
            Type.LONG -> divLong!!(t, number2 as Long)
            Type.SHORT -> divShort!!(t, number2 as Short)
        }
    }

    fun pow(number: Number, n: Number): Number {
        val t = number as T
        if (t !is Double && t !is Float) {
            throw IllegalArgumentException("pow methods can only be used with Double and Float")
        }

        return when (type(n)) {
            Type.DOUBLE -> powDouble!!(t, n as Double)
            Type.FLOAT -> powFloat!!(t, n as Float)
            Type.INT -> powInt!!(t, n as Int)
            else -> {
                throw UnsupportedOperationException()
            }
        }
    }

    fun compareTo(number: Number, number2: Number): Int {
        val t = number as T
        return when (type(number2)) {
            Type.BYTE -> compareToByte!!(t, number2 as Byte)
            Type.DOUBLE -> compareToDouble!!(t, number2 as Double)
            Type.FLOAT -> compareToFloat!!(t, number2 as Float)
            Type.INT -> compareToInt!!(t, number2 as Int)
            Type.LONG -> compareToLong!!(t, number2 as Long)
            Type.SHORT -> compareToShort!!(t, number2 as Short)
        }
    }

    enum class Type {
        BYTE,
        DOUBLE,
        FLOAT,
        INT,
        LONG,
        SHORT
    }
}