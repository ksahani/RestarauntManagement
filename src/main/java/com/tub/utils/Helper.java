package com.tub.utils;

import com.tub.beans.Recipe;

public class Helper {
	public static void computeRecipeCost(Recipe recipe) {
		recipe.setCostPrice(0.0);
		recipe.getIngredients().forEach(ingredient -> {
			recipe.setCostPrice(recipe.getCostPrice() + ingredient.getNumOfUnits()*ingredient.getUnitCost());
		});
	}
}
