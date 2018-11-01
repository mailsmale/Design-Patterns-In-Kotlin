package abstractFactory

import java.lang.IllegalStateException

interface FactoryProduct

interface Shape : FactoryProduct {
    fun draw()
}

interface Color : FactoryProduct {
    fun fill()
}


class Rectangle : Shape {
    override fun draw() {
        println("Inside Rectangle draw method")
    }

}
class Square : Shape {
    override fun draw() {
        println("Inside Square draw method")
    }

}

class Red : Color {
    override fun fill() {
        println("Inside Red fill method")
    }
}

class Yellow : Color {
    override fun fill() {
        println("Inside Yellow fill method")
    }
}

abstract class AbstractFactory {

    abstract fun getColor(shapeColor: String?): Color?
    abstract fun getShape(shapeType: String?): Shape?

    companion object {
        inline fun <reified T: AbstractFactory> createFactory() : AbstractFactory = when(T::class) {
            ShapeFactory::class -> ShapeFactory()
            ColorFactory::class -> ColorFactory()
            else -> throw IllegalStateException()
        }
    }
}

class ShapeFactory : AbstractFactory() {
    override fun getColor(shapeColor: String?): Color? {
        return null
    }

    override fun getShape(shapeType: String?): Shape? {
        return when {
            null == shapeType -> null
            shapeType.equals("RECTANGLE", true) -> Rectangle()
            shapeType.equals("SQUARE", true) -> Square()
            else -> throw IllegalStateException()
        }
    }

}

class ColorFactory : AbstractFactory() {
    override fun getShape(shapeType: String?): Shape? {
        return null
    }

    override fun getColor(shapeColor: String?): Color? {
        return when {
            null == shapeColor -> null
            shapeColor.equals("RED", true) -> Red()
            shapeColor.equals("YELLOW", true) -> Yellow()
            else -> throw IllegalStateException()
        }
    }

}

fun main(args: Array<String>) {
    val factory = AbstractFactory.createFactory<ShapeFactory>()
    println (factory.getColor("RED"))
    println (factory.getShape("SQUARE"))
}

