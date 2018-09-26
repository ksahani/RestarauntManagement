package com.tub.consumption;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import org.apache.commons.beanutils.BeanUtils;

import com.tub.beans.Ingredient;
import com.tub.beans.Recipe;
import com.tub.utils.Constants;
import com.tub.utils.Helper;

/**
 * @author Kuwar
 *
 *
 */
public class ConsumptionCalculator {
	public static void main(String[] args) throws IOException {
		// load recipes
		Map<Integer, Ingredient> ingredients = loadProducts();
		System.out.println();
		System.out.println(ingredients);
		Map<Integer, Recipe> recipies = loadRecipes(ingredients);
		
		Map<Integer, Integer> orderCount = new HashMap<>();
		Map<Integer, Double> ingredientQuantityUsed = new HashMap<>();
		System.out.println(recipies);
		//System.out.println(ingredients);
		
		orderCount.put(1, 100);
		orderCount.put(2, 3);
		orderCount.forEach((recipeId, quantity) -> {
			Recipe recipe = recipies.get(recipeId);
			recipe.getIngredients().forEach(ingredient -> {
				if (!ingredientQuantityUsed.containsKey(ingredient.getIngredientId())) {
					ingredientQuantityUsed.put(ingredient.getIngredientId(), ((ingredient.getNumOfUnits()*1.0)/recipe.getQuantity()) * quantity);
				} else {
					ingredientQuantityUsed.put(ingredient.getIngredientId(),
							ingredientQuantityUsed.get(ingredient.getIngredientId()) + ((ingredient.getNumOfUnits()*1.0)/recipe.getQuantity()) * quantity);
				}
			});
		});
		
		ingredientQuantityUsed.forEach((ingredient,quantity) -> {
			System.out.println(ingredients.get(ingredient).getIngredientName() + ":" +  quantity);
		});

	}

	public static Map<Integer, Ingredient> loadProducts() throws IOException {
		Stream<String> lines = Files.lines(
				Paths.get("C:/Users/Kuwar/eclipse-workspace/Recipe/src/main/resources/com/tub/inventory/productIndex"));
		Map<Integer, Ingredient> ingredientMap = new HashMap<>();

		lines.forEach(s -> {
			int i = 0;
			Ingredient ingredient = new Ingredient();
			try {
				for (String prop : s.split("\\,", -1)) {
					if (prop != null) {
						BeanUtils.setProperty(ingredient, Constants.productHeader[i], prop);
					}
					i++;
				}

			} catch (IllegalAccessException | InvocationTargetException e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
			ingredientMap.put(ingredient.getIngredientId(), ingredient);

		});
		lines.close();

		return ingredientMap;
	}

	public static Map<Integer, Recipe> loadRecipes(Map<Integer, Ingredient> ingredientMaster) throws IOException {
		Stream<String> lines = Files.lines(
				Paths.get("C:/Users/Kuwar/eclipse-workspace/Recipe/src/main/resources/com/tub/recipe/bakeryItems"));
		List<Recipe> recipies = new ArrayList<>();
		Map<Integer, Recipe> recipeMap = new HashMap<>();
		lines.forEach(recipeEntry -> {
			List<Ingredient> ingredients = new ArrayList<>();
			String productsUsed = recipeEntry.split("\\|",-1)[1];
			for (String item : productsUsed.split("\\,",-1)) {
				Ingredient ingredient = new Ingredient(Integer.parseInt(item.split("\\~",-1)[0]),
						Double.parseDouble(item.split("\\~")[1]));
				ingredients.add(ingredient);
				ingredient.setIngredientName(ingredientMaster.get(ingredient.getIngredientId()).getIngredientName());
				ingredient.setUnit(ingredientMaster.get(ingredient.getIngredientId()).getUnit());
				ingredient.setUnitCost(ingredientMaster.get(ingredient.getIngredientId()).getUnitCost());
				;
			}

			Recipe recipe = new Recipe(Integer.parseInt(recipeEntry.split("\\|",-1)[0].split("\\~",-1)[0]),
					recipeEntry.split("\\|",-1)[0].split("\\~",-1)[1], ingredients,Integer.parseInt(recipeEntry.split("\\|",-1)[0].split("\\~",-1)[2]));
			Helper.computeRecipeCost(recipe);
			recipies.add(recipe);
			recipeMap.put(recipe.getRecipeId(), recipe);
		});

		lines.close();
		return recipeMap;
	}
}
