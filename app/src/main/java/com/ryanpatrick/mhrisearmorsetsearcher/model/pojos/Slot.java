package com.ryanpatrick.mhrisearmorsetsearcher.model.pojos;

import com.ryanpatrick.mhrisearmorsetsearcher.model.pojos.Gem;

public class Slot {
	private int level;
	private boolean isFilled;
	private Gem gem;

	public int getLevel(){
		return level;
	}

	public boolean isIsFilled(){
		return isFilled;
	}

	public Gem getGem(){
		return gem;
	}
}
