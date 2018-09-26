package com.tub.beans;

import java.util.List;

public class Recipe {
	private Integer recipeId;
	private String recipeName;
	private List<Ingredient> ingredients;
	private Double costPrice;
	private Double sellingPrice;
	private Integer quantity;
	
	
	
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public Integer getRecipeId() {
		return recipeId;
	}
	public void setRecipeId(Integer recipeId) {
		this.recipeId = recipeId;
	}
	public String getRecipeName() {
		return recipeName;
	}
	public void setRecipeName(String recipeName) {
		this.recipeName = recipeName;
	}
	public List<Ingredient> getIngredients() {
		return ingredients;
	}
	public void setIngredients(List<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}
	public Double getSellingPrice() {
		return sellingPrice;
	}
	public void setSellingPrice(Double sellingPrice) {
		this.sellingPrice = sellingPrice;
	}
	
	
	
	public Double getCostPrice() {
		return costPrice;
	}
	public void setCostPrice(Double costPrice) {
		this.costPrice = costPrice;
	}
	public Recipe(Integer recipeId, String recipeName, List<Ingredient> ingredients, Integer quantity) {
		super();
		this.recipeId = recipeId;
		this.recipeName = recipeName;
		this.ingredients = ingredients;
		this.quantity = quantity;
	}
	@Override
	public String toString() {
		return "Recipe [recipeId=" + recipeId + ", recipeName=\"" + recipeName + "\", ingredients=" + ingredients
				+ ", costPrice=" + costPrice + ", sellingPrice=" + sellingPrice + ", quantity=" + quantity + "]";
	}
		
	
	
	
}
