package com.tub.beans;

public class Ingredient {
	private int ingredientId;
	private String ingredientName;
	private Double numOfUnits;
	private String unit;
	private Double unitCost;
	
	
	
	
	public Ingredient() {
		super();
	}



	public Ingredient(int ingredientId, Double numOfUnits) {
		super();
		this.ingredientId = ingredientId;
		this.numOfUnits = numOfUnits;
	}



	public int getIngredientId() {
		return ingredientId;
	}



	public void setIngredientId(int ingredientId) {
		this.ingredientId = ingredientId;
	}



	public String getIngredientName() {
		return ingredientName;
	}



	public void setIngredientName(String ingredientName) {
		this.ingredientName = ingredientName;
	}



	public Double getNumOfUnits() {
		return numOfUnits;
	}



	public void setNumOfUnits(Double numOfUnits) {
		this.numOfUnits = numOfUnits;
	}



	public String getUnit() {
		return unit;
	}



	public void setUnit(String unit) {
		this.unit = unit;
	}



	public Double getUnitCost() {
		return unitCost;
	}



	public void setUnitCost(Double unitCost) {
		this.unitCost = unitCost;
	}



	@Override
	public String toString() {
		return "Ingredient [ingredientId=" + ingredientId + ", ingredientName= \"" + ingredientName + "\", numOfUnits="
				+ numOfUnits + ", unit=" + unit + ", unitCost=" + unitCost + "]";
	}
	
	

	
	
	
}
