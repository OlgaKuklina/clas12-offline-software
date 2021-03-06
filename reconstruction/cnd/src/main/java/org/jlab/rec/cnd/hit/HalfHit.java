package org.jlab.rec.cnd.hit;

import org.jlab.rec.cnd.costants.CalibrationConstantsLoader;
import org.jlab.rec.cnd.costants.Parameters;

public class HalfHit {

	// In the constructor below, bank_index refers to index in the raw CND bank, 
	// flag is 0 if it's real data or the "direct" signal in the simulation, 1 if it is the "indirect" signal in the simulation.

	public HalfHit(int sector, int layer, int component, int adc, int tdc, int bank_index) 
	{
		this._sector = sector;
		this._layer = layer;
		this._component = component;	
		this._bankindex = bank_index;

		//first step of the adc and tdc processing
		this._Eatt = (double)adc  * ((0.1956*CalibrationConstantsLoader.THICKNESS[0])/(2.)); // the 2 accounts for the splitting of the deposited energy along the two coupled paddles
		this._Tprop = ((double)tdc * CalibrationConstantsLoader.TDCTOTIMESLOPE[sector-1][layer-1][component-1])+ CalibrationConstantsLoader.TDCTOTIMEOFFSET[sector-1][layer-1][component-1] + CalibrationConstantsLoader.TIMEOFFSETSECT[sector-1][layer-1] + CalibrationConstantsLoader.TIMEOFFSETSLR[sector-1][layer-1] ; // And other constants!
	}

	private double _Eatt;      // Attenuated energy (MeV) at the upstream end of the paddle 
	private double _Tprop;     // Time (ns) at the upstream end of the paddle

	private int _sector;       // sector (block) of the CND 				
	private int _layer;        // layer in which the signal is registered
	private int _component;    // component (paddle) with which the signal is associated

	private int _bankindex;    // Index of the signal in the raw CND bank

	public int Sector() {
		return _sector;
	}	

	public int Layer() {
		return _layer;
	}	

	public int Component() {
		return _component;
	}

	public int BankIndex() {
		return _bankindex;
	}


	public double Eatt() {
		return _Eatt;
	}

	public double Tprop() {
		return _Tprop;
	}


}
