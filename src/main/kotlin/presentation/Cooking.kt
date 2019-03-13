package presentation

import kotlinx.coroutines.*

/**
 * @author Adriano Belfort (adriano.belfort@playkids.com) on 2019-03-13
 */

typealias Ingredient = String
typealias CookedIngredient = String
typealias Food = String

suspend fun prepareIngredient(ingredient: Ingredient, cookingTime: Long): CookedIngredient {
    println("🍛 I'll prepare $ingredient now (takes $cookingTime seconds) (Chef ${Thread.currentThread().name})")
    delay(cookingTime * 1000)
    return "cooked $ingredient"
}

fun assemble(name: String, vararg cookedIngredient: CookedIngredient): Food {
    return "$name (contains ${cookedIngredient.joinToString(", ")})"
}

fun eat(food: Food) {
    println("😋 I'm eating a delicious $food")
}

fun CoroutineScope.cook(dispatcher: CoroutineDispatcher): Job {
    return launch(dispatcher) {
        val plateName = "beef with fried eggs"

        val startTime = System.currentTimeMillis()

        val rice = prepareIngredient("rice", 8)
        val beans = prepareIngredient("beans", 15)
        val beef = prepareIngredient("beef", 10)
        val eggs = prepareIngredient("eggs", 4)

        val plate = assemble(plateName, rice, beans, beef, eggs)

        val endTime = System.currentTimeMillis()

        val seconds = (endTime - startTime) / 1000

        println("😋 After $seconds seconds my $plateName is ready!")

        eat(plate)
    }
}
