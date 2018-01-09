/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erp.mtrn.data;

import java.util.ArrayList;
import sa.lib.SLibUtils;

/**
 *
 * @author Irving Sánchez, Daniel López
 */
public class SGuiDpsEntryPrice {
    private double mdQtyUsedOther;
    private double mdQtyUsedOwn;
    
    private SDataDpsEntryPrice moDataDpsSourceEntryPrice;
    private ArrayList<SDataDpsEntry> maDataDpsDestinyEntries;
    
    public SGuiDpsEntryPrice(SDataDpsEntryPrice dataDpsSourceEntryPrice) {
        moDataDpsSourceEntryPrice = dataDpsSourceEntryPrice;
        maDataDpsDestinyEntries = new ArrayList<>();
    }

    public void setQtyUsedOther(double qty) { mdQtyUsedOther = qty; }
    public void setQtyUsedOwn(double qty) { mdQtyUsedOwn = qty; }
    public double getQtyUsedOther() { return mdQtyUsedOther; }
    public double getQtyUsedOwn() { return mdQtyUsedOwn; }

    public SDataDpsEntryPrice getDataDpsEntryPrice() { return moDataDpsSourceEntryPrice; }
    public ArrayList<SDataDpsEntry> getDataDpsDestinyEntries() { return maDataDpsDestinyEntries; }

    public double obtainQtyAvailable() {
        updateQtyOwn();
        return SLibUtils.round(moDataDpsSourceEntryPrice.getOriginalQuantity() - mdQtyUsedOther - mdQtyUsedOwn, SLibUtils.getDecimalFormatAmountUnitary().getMaximumFractionDigits());
    }
    
    public double obtainQtyAvailable(SDataDpsEntry dataDpsDestinyEntry) {
        updateQtyOwn();
        return SLibUtils.round(obtainQtyAvailable() + dataDpsDestinyEntry.getOriginalQuantity(), SLibUtils.getDecimalFormatAmountUnitary().getMaximumFractionDigits());
    }

    public void updateQtyOwn() {
        mdQtyUsedOwn = 0;
        for (SDataDpsEntry dpsDestinyEntry : maDataDpsDestinyEntries) {
            mdQtyUsedOwn += dpsDestinyEntry.getOriginalQuantity();
        }
    }
    
    public void addDataDpsDestinyEntry(SDataDpsEntry dataDpsDestinyEntry) {
        maDataDpsDestinyEntries.add(dataDpsDestinyEntry);
        updateQtyOwn();
    }
    
    public void removeDataDpsDestinyEntry(SDataDpsEntry dataDpsDestinyEntry) {
        maDataDpsDestinyEntries.remove(dataDpsDestinyEntry);
        updateQtyOwn();
    }
    
    public void updateDataDpsDestinyEntry(SDataDpsEntry oldDataDpsDestinyEntry, SDataDpsEntry newDataDpsDestinyEntry) {
        removeDataDpsDestinyEntry(oldDataDpsDestinyEntry);
        addDataDpsDestinyEntry(newDataDpsDestinyEntry);
    
    }
}
