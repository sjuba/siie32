/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package erp.mod.hrs.db;

import erp.mod.SModSysConsts;
import java.util.ArrayList;
import java.util.Date;
import sa.lib.SLibConsts;
import sa.lib.SLibTimeUtils;
import sa.lib.SLibUtils;

/**
 *
 * @author Juan Barajas, Néstor Ávalos, Sergio Flores, Edwin Carmona
 */
public class SHrsPayroll {

    protected SDbConfig moConfig;
    protected SDbWorkingDaySettings moWorkingDaySettings;
    protected SDbPayroll moPayroll;
    protected SHrsPayrollDataProvider moPayrollDataProvider;

    protected ArrayList<SDbLoanTypeAdjustment> maLoanTypeAdjustment;
    protected ArrayList<SDbUma> maUmas;
    protected ArrayList<SDbHoliday> maHolidays;
    protected ArrayList<SDbTaxTable> maTaxTables;
    protected ArrayList<SDbTaxSubsidyTable> maTaxSubsidyTables;
    protected ArrayList<SDbSsContributionTable> maSsContributionTables;
    protected ArrayList<SDbBenefitTable> maBenefitTables;
    protected ArrayList<SHrsBenefitTableByAnniversary> maBenefitTablesAnniversarys;
    protected ArrayList<SDbMwzTypeWage> maMwzTypeWages;
    protected ArrayList<SDbEarning> maEarnigs;
    protected ArrayList<SDbDeduction> maDeductions;
    protected ArrayList<SDbAutomaticEarning> maAutomaticEarnings;
    protected ArrayList<SDbAutomaticDeduction> maAutomaticDeductions;
    protected ArrayList<SDbEmployee> maEmployees;
    protected ArrayList<SHrsPayrollReceipt> maReceipts;

    protected boolean mbIsLastPayrollPeriod;
    
    public SHrsPayroll() {
        moConfig = null;
        moWorkingDaySettings = null;
        moPayroll = null;
        moPayrollDataProvider = null;

        maLoanTypeAdjustment = new ArrayList<>();
        maUmas = new ArrayList<>();
        maHolidays = new ArrayList<>();
        maTaxTables = new ArrayList<>();
        maTaxSubsidyTables = new ArrayList<>();
        maSsContributionTables = new ArrayList<>();
        maBenefitTables = new ArrayList<>();
        maBenefitTablesAnniversarys = new ArrayList<>();
        maMwzTypeWages = new ArrayList<>();
        maEarnigs = new ArrayList<>();
        maDeductions = new ArrayList<>();
        maAutomaticEarnings = new ArrayList<>();
        maAutomaticDeductions = new ArrayList<>();
        maEmployees = new ArrayList<>();
        maReceipts = new ArrayList<>();
        mbIsLastPayrollPeriod = false;
    }

    /*
     * Private methods:
     */

    private ArrayList<SHrsPayrollReceiptEarning> getPayrollReceiptEarnings(final SHrsPayrollReceipt hrsPayrollReceipt, final int employeeId, final Date dateStart, final Date dateEnd) throws Exception {
        int move = 0;
        double unit = 0;
        double unitAlleged = 0;
        double vacationDays = 0;
        double amount_unt = 0;
        double amount = 0;
        SDbPayrollReceiptEarning payrollReceiptEarning = null;
        SHrsPayrollReceiptEarning hrsPayrollReceiptEarning = null;

        ArrayList<SHrsPayrollReceiptEarning> aHrsPayrollReceiptEarnings = new ArrayList<SHrsPayrollReceiptEarning>();
        ArrayList<SDbAutomaticEarning> aAutomaticEarnings = new ArrayList<SDbAutomaticEarning>();

        // Get earnings of withholding:

        move = 1;
        /* XXX jbarajas (07-09-2015) this field is for correction purposes of deductions, not for add automatic earning.
        for (SDbEarning earning : maEarnigs) {

            if (earning.isWithholding()) {
                hrsPayrollReceiptEarning = new SHrsPayrollReceiptEarning();
                hrsPayrollReceiptEarning.setEarning(earning);

                payrollReceiptEarning = createHrsPayrollReceiptEarning(employeeId, 0, 0, 0, true, earning,
                    SLibConsts.UNDEFINED, SLibConsts.UNDEFINED, earning.getFkLoanTypeId(), hrsPayrollReceipt);

                hrsPayrollReceiptEarning.setReceiptEarning(payrollReceiptEarning);
                hrsPayrollReceiptEarning.setPkMoveId(move);
                hrsPayrollReceiptEarning.setHrsReceipt(hrsPayrollReceipt);
                aHrsPayrollReceiptEarnings.add(hrsPayrollReceiptEarning);

                move++;
            }
        }
        */

        // Get automatic earnings:

        aAutomaticEarnings = getAutomaticEarnings(employeeId, dateStart, dateEnd, moPayroll.getFkPaysheetTypeId());
        for (SDbAutomaticEarning automaticEarning : aAutomaticEarnings) {

            hrsPayrollReceiptEarning = new SHrsPayrollReceiptEarning();
            for (SDbEarning earning : maEarnigs) {
                if (earning.getPkEarningId() == automaticEarning.getPkEarningId()) {
                    hrsPayrollReceiptEarning.setEarning(earning);
                    
                    
                    if (earning.getFkEarningComputationTypeId() == SModSysConsts.HRSS_TP_EAR_COMP_AMT) {
                        amount_unt = automaticEarning.getAmountUnitary();
                    }
                    /* XXX (jbarajas, 2016-09-12) concentrate in one place the calculation of perceptions and deductions.
                    else if (earning.getFkEarningComputationTypeId() == SModSysConsts.HRSS_TP_EAR_COMP_DAY) {
                        amount_unt = hrsPayrollReceipt.getReceipt().getPaymentDaily();
                    }
                    else if (earning.getFkEarningComputationTypeId() == SModSysConsts.HRSS_TP_EAR_COMP_HRS) {
                        amount_unt = hrsPayrollReceipt.getReceipt().getPaymentHourly();
                    }
                    else if (earning.getFkEarningComputationTypeId() == SModSysConsts.HRSS_TP_EAR_COMP_PER_DAY) {
                        amount_unt = hrsPayrollReceipt.getReceipt().getPaymentDaily() * earning.getPayPercentage();
                    }
                    else if (earning.getFkEarningComputationTypeId() == SModSysConsts.HRSS_TP_EAR_COMP_PER_HRS) {
                        amount_unt = hrsPayrollReceipt.getReceipt().getPaymentHourly() * earning.getPayPercentage();
                    }
                    unit = !earning.isDaysAdjustment() ? automaticEarning.getUnits() * hrsPayrollReceipt.getHrsEmployee().getEmployeeDays().getFactorCalendar() : (automaticEarning.getUnits() * hrsPayrollReceipt.getHrsEmployee().getEmployeeDays().getFactorCalendar() * hrsPayrollReceipt.getHrsEmployee().getEmployeeDays().getFactorDaysPaid());
                    amount = SLibUtils.round((unit * amount_unt * earning.getUnitsFactor()), SLibUtils.DecimalFormatValue2D.getMaximumFractionDigits());
                    */
                    break;
                }
            }

            /* XXX (jbarajas, 2016-09-12) concentrate in one place the calculation of perceptions and deductions.
            payrollReceiptEarning = createHrsPayrollReceiptEarning(employeeId, automaticEarning.getUnits(), amount_unt, amount, true,
                    hrsPayrollReceiptEarning.getEarning(), automaticEarning.getFkLoanEmployeeId_n(), automaticEarning.getFkLoanLoanId_n(),
                    automaticEarning.getFkLoanTypeId_n(), hrsPayrollReceipt);
            */
            
            payrollReceiptEarning = createHrsPayrollReceiptEarning(hrsPayrollReceipt, null, automaticEarning.getUnits(), amount_unt, true, hrsPayrollReceiptEarning.getEarning(), 
                    automaticEarning.getFkLoanEmployeeId_n(), automaticEarning.getFkLoanLoanId_n(), automaticEarning.getFkLoanTypeId_n(), move);

            hrsPayrollReceiptEarning.setReceiptEarning(payrollReceiptEarning);
            hrsPayrollReceiptEarning.setPkMoveId(move);
            hrsPayrollReceiptEarning.setHrsReceipt(hrsPayrollReceipt);
            aHrsPayrollReceiptEarnings.add(hrsPayrollReceiptEarning);

            move++;
        }
        unit = 0;
        amount_unt = 0;
        amount = 0;

        if (moPayroll.isNormal()) {
            // Add earning and vacations:

            unitAlleged = hrsPayrollReceipt.getHrsEmployee().getEmployeeDays().getDaysWorked();

            for (SDbAbsenceConsumption receiptAbsenceConsumption: hrsPayrollReceipt.getAbsenceConsumptions()) {
                if (receiptAbsenceConsumption.getAbsence().getFkAbsenceClassId() == SModSysConsts.HRSU_CL_ABS_VAC) {
                    vacationDays += receiptAbsenceConsumption.getEffectiveDays();
                }
            }

            /*
            for (SDbPayrollReceiptDay receiptDay: hrsPayrollReceipt.getEffectiveDays()) {
                if (receiptDay.getFkDayTypeId() == SModSysConsts.HRSS_TP_DAY_WRK || receiptDay.getFkDayTypeId() == SModSysConsts.HRSS_TP_DAY_HOL) {
                    earningDays++;
                }
                else if (receiptDay.getFkDayTypeId() == SModSysConsts.HRSS_TP_DAY_ABS && receiptDay.getFkAbsenceClassId_n() == SModSysConsts.HRSU_CL_ABS_VAC) {
                    vacationDays++;
                }
            }
            */

            if (unitAlleged > 0 && moConfig.getFkEarningEarningId_n() == SLibConsts.UNDEFINED) {
                throw new Exception(SLibConsts.ERR_MSG_OPTION_UNKNOWN + " (Configuración percepción normal)");
            }

            if (moConfig.getFkEarningEarningId_n() != SLibConsts.UNDEFINED && unitAlleged > 0) {
                for (SDbEarning earning : maEarnigs) {
                    
                    if (earning.getFkEarningComputationTypeId() == SModSysConsts.HRSS_TP_EAR_COMP_AMT) {
                        amount_unt = unitAlleged;
                    }
                    /* XXX (jbarajas, 2016-09-12) concentrate in one place the calculation of perceptions and deductions.
                    else if (earning.getFkEarningComputationTypeId() == SModSysConsts.HRSS_TP_EAR_COMP_DAY) {
                        amount_unt = hrsPayrollReceipt.getReceipt().getPaymentDaily();
                    }
                    else if (earning.getFkEarningComputationTypeId() == SModSysConsts.HRSS_TP_EAR_COMP_HRS) {
                        amount_unt = hrsPayrollReceipt.getReceipt().getPaymentHourly();
                    }
                    else if (earning.getFkEarningComputationTypeId() == SModSysConsts.HRSS_TP_EAR_COMP_PER_DAY) {
                        amount_unt = hrsPayrollReceipt.getReceipt().getPaymentDaily() * earning.getPayPercentage();
                    }
                    else if (earning.getFkEarningComputationTypeId() == SModSysConsts.HRSS_TP_EAR_COMP_PER_HRS) {
                        amount_unt = hrsPayrollReceipt.getReceipt().getPaymentHourly() * earning.getPayPercentage();
                    }
                    unit = !earning.isDaysAdjustment() ? unitAlleged * hrsPayrollReceipt.getHrsEmployee().getEmployeeDays().getFactorCalendar() : (unitAlleged * hrsPayrollReceipt.getHrsEmployee().getEmployeeDays().getFactorCalendar() * hrsPayrollReceipt.getHrsEmployee().getEmployeeDays().getFactorDaysPaid());
                    amount = SLibUtils.round((unit * amount_unt), SLibUtils.DecimalFormatValue2D.getMaximumFractionDigits());
                    */

                    if (SLibUtils.compareKeys(earning.getPrimaryKey(), new int[] { moConfig.getFkEarningEarningId_n() })) {
                        hrsPayrollReceiptEarning = new SHrsPayrollReceiptEarning();
                        hrsPayrollReceiptEarning.setEarning(earning);

                        /* XXX (jbarajas, 2016-09-12) concentrate in one place the calculation of perceptions and deductions.
                        payrollReceiptEarning = createHrsPayrollReceiptEarning(employeeId, unitAlleged, amount_unt, amount, true, earning,
                            SLibConsts.UNDEFINED, SLibConsts.UNDEFINED, earning.getFkLoanTypeId(), hrsPayrollReceipt);
                        */

                        payrollReceiptEarning = createHrsPayrollReceiptEarning(hrsPayrollReceipt, null, unitAlleged, amount_unt, true, earning, 
                            SLibConsts.UNDEFINED, SLibConsts.UNDEFINED, earning.getFkLoanTypeId(), move);
                        
                        hrsPayrollReceiptEarning.setReceiptEarning(payrollReceiptEarning);
                        hrsPayrollReceiptEarning.setPkMoveId(move);
                        hrsPayrollReceiptEarning.setHrsReceipt(hrsPayrollReceipt);
                        aHrsPayrollReceiptEarnings.add(hrsPayrollReceiptEarning);

                        move++;
                    }
                }
            }

            /*
            if (vacationDays > 0 && moConfig.getFkEarningVacationsId_n() == SLibConsts.UNDEFINED) {
                throw new Exception(SLibConsts.ERR_MSG_OPTION_UNKNOWN + " (Configuración vacaciones)");
            }

            if (moConfig.getFkEarningVacationsId_n() != SLibConsts.UNDEFINED && vacationDays > 0) {
                for (SDbEarning earning : maEarnigs) {
                    if (SLibUtils.compareKeys(earning.getPrimaryKey(), new int[] { moConfig.getFkEarningVacationsId_n() })) {
                        hrsPayrollReceiptEarning = new SHrsPayrollReceiptEarning();
                        hrsPayrollReceiptEarning.setEarning(earning);

                        payrollReceiptEarning = createHrsPayrollReceiptEarning(employeeId, vacationDays, 0, 0, true, earning,
                            SLibConsts.UNDEFINED, SLibConsts.UNDEFINED, earning.getFkLoanTypeId(), hrsPayrollReceipt);

                        hrsPayrollReceiptEarning.setReceiptEarning(payrollReceiptEarning);
                        hrsPayrollReceiptEarning.setPkMoveId(move);
                        hrsPayrollReceiptEarning.setHrsReceipt(hrsPayrollReceipt);
                        aHrsPayrollReceiptEarnings.add(hrsPayrollReceiptEarning);

                        move++;
                    }
                }
            }
            */

            for (SHrsPayrollReceiptEarning hrsPayrollReceiptEarningRow : getHrsPayrollReceiptEarningAbsence(hrsPayrollReceipt.getAbsenceConsumptions(), hrsPayrollReceipt)) {
                aHrsPayrollReceiptEarnings.add(hrsPayrollReceiptEarningRow);
            }
        }

        return aHrsPayrollReceiptEarnings;
    }
    
    public ArrayList<SDbAbsenceConsumption> getAbsenceConsumptionDays(final SHrsPayrollReceipt hrsPayrollReceipt, final SHrsEmployee hrsEmployee) {
        int consumptionPreviousDays = 0;
        double receiptDays = 0;
        double consumptionEffectiveDays = 0;
        int consumptionCurrentDays = 0;
        int businessDays = 0;
        int diferencePendingDays = 0;
        int pendigEffectiveDays = 0;
        double workingDaysAbsence = 0;
        boolean isConsumptionlast = false;
        Date dateStartConsumption = null;
        Date dateEndConsumption = null;
        SDbAbsenceConsumption absenceConsumption = null;
        ArrayList<SDbAbsenceConsumption> aAbsenceConsumption = null;
        
        try {
            aAbsenceConsumption = new ArrayList<SDbAbsenceConsumption>();
            
            receiptDays = hrsEmployee.getEmployeeDays().getReceiptDays();
            workingDaysAbsence = hrsEmployee.getEmployeeDays().getBusinessDays();
            
            for (SDbAbsence absence : hrsEmployee.getAbsences()) {
                isConsumptionlast = false;
                consumptionPreviousDays = SHrsUtils.getConsumptionPreviousDays(absence, hrsEmployee);
                consumptionCurrentDays = (absence.getEffectiveDays() - consumptionPreviousDays);
                
                if (consumptionCurrentDays > 0 && !absence.isClosed()) {
                    if (absence.getDateStart().compareTo(hrsPayrollReceipt.getHrsPayroll().getPayroll().getDateEnd()) <= 0) {
                        /* XXX jbarajas 2016-03-22 modified for consumption of days naturals
                        if (consumptionCurrentDays > (workingDaysAbsence - consumptionEffectiveDays)) {
                            consumptionCurrentDays = (int) (workingDaysAbsence - consumptionEffectiveDays);
                        }
                        */
                        if (consumptionCurrentDays > ((absence.getFkAbsenceClassId() == SModSysConsts.HRSU_CL_ABS_DIS ? receiptDays : workingDaysAbsence) - consumptionEffectiveDays)) {
                            consumptionCurrentDays = (int) ((absence.getFkAbsenceClassId() == SModSysConsts.HRSU_CL_ABS_DIS ? receiptDays : workingDaysAbsence) - consumptionEffectiveDays);
                        }
                        else {
                            isConsumptionlast = true;
                        }

                        dateStartConsumption = consumptionPreviousDays == 0 ? absence.getDateStart() : SLibTimeUtils.addDate(SHrsUtils.getConsumptionDateLast(absence, hrsEmployee), 0, 0, 1);
                        dateEndConsumption = isConsumptionlast ? absence.getDateEnd() : SLibTimeUtils.addDate(dateStartConsumption, 0, 0, (consumptionCurrentDays - 1));

                        businessDays = consumptionCurrentDays - SHrsUtils.getEmployeeBusinessDays(dateStartConsumption, dateEndConsumption, maHolidays, moWorkingDaySettings);
                        
                        if (businessDays > 0) {
                            while (businessDays > 0) {
                                diferencePendingDays = (int) SLibTimeUtils.getDaysDiff(absence.getDateEnd(), SLibTimeUtils.addDate(dateEndConsumption, 0, 0, businessDays));
                                pendigEffectiveDays = (absence.getEffectiveDays() - consumptionPreviousDays) - consumptionCurrentDays;

                                if (pendigEffectiveDays > diferencePendingDays) {
                                    businessDays--;
                                }
                                else {
                                    dateEndConsumption = SLibTimeUtils.addDate(dateEndConsumption, 0, 0, businessDays);
                                    businessDays = -1;
                                }
                            }
                        }
                        
                        absenceConsumption = hrsPayrollReceipt.createAbsenceConsumption(absence, dateStartConsumption, dateEndConsumption, consumptionCurrentDays);

                        if (hrsPayrollReceipt.validateAbsenceConsumption(absence, absenceConsumption)) {
                            aAbsenceConsumption.add(absenceConsumption);
                        }

                        consumptionEffectiveDays += consumptionCurrentDays;
                        //if (consumptionEffectiveDays >= workingDaysAbsence) {  XXX jbarajas 2016-03-22 modified for consumption of days naturals
                        if (consumptionEffectiveDays >= (absence.getFkAbsenceClassId() == SModSysConsts.HRSU_CL_ABS_DIS ? receiptDays : workingDaysAbsence)) {
                            break;
                        }
                    }
                }
            }
        }
        catch (Exception e) {
            SLibUtils.showException(this, e);
        }
        
        return aAbsenceConsumption;
    }

    private ArrayList<SHrsPayrollReceiptDeduction> getPayrollReceiptDeductions(final SHrsPayrollReceipt hrsPayrollReceipt, final int employeeId, final Date dateStart, final Date dateEnd) {
        int move = 0;
        double amountLoan = 0;
        double balanceLoan = 0;
        SDbPayrollReceiptDeduction payrollReceiptDeduction = null;
        SHrsPayrollReceiptDeduction hrsPayrollReceiptDeduction = null;

        ArrayList<SDbAutomaticDeduction> aAutomaticDeductions;
        ArrayList<SHrsPayrollReceiptDeduction> aHrsPayrollReceiptDeductions = new ArrayList<>();

        try {
            if (moPayroll.isNormal()) {
                // Get earnings of withholding:

                move = 1;
                for (SDbDeduction deduction : maDeductions) {
                    if (deduction.isWithholding()) {
                        hrsPayrollReceiptDeduction = new SHrsPayrollReceiptDeduction();
                        hrsPayrollReceiptDeduction.setDeduction(deduction);

                        payrollReceiptDeduction = createHrsPayrollReceiptDeduction(employeeId, 0, 0, 0, deduction,
                            SLibConsts.UNDEFINED, SLibConsts.UNDEFINED, deduction.getFkLoanTypeId());

                        payrollReceiptDeduction.setAutomatic(true);
                        hrsPayrollReceiptDeduction.setReceiptDeduction(payrollReceiptDeduction);
                        hrsPayrollReceiptDeduction.setPkMoveId(move);
                        hrsPayrollReceiptDeduction.setHrsReceipt(hrsPayrollReceipt);
                        aHrsPayrollReceiptDeductions.add(hrsPayrollReceiptDeduction);

                        move++;
                    }
                }
            }

            // Get automatic deductions:

            aAutomaticDeductions = getAutomaticDeductions(employeeId, dateStart, dateEnd, moPayroll.getFkPaysheetTypeId());
            for (SDbAutomaticDeduction automaticDeduction : aAutomaticDeductions) {

                hrsPayrollReceiptDeduction = new SHrsPayrollReceiptDeduction();
                for (SDbDeduction deduction : maDeductions) {
                    if (deduction.getPkDeductionId() == automaticDeduction.getPkDeductionId()) {
                        hrsPayrollReceiptDeduction.setDeduction(deduction);
                        break;
                    }
                }

                payrollReceiptDeduction = createHrsPayrollReceiptDeduction(employeeId, automaticDeduction.getUnits(), automaticDeduction.getAmountUnitary(), automaticDeduction.getAmount_r(),
                        hrsPayrollReceiptDeduction.getDeduction(), automaticDeduction.getFkLoanEmployeeId_n(), automaticDeduction.getFkLoanLoanId_n(),
                        automaticDeduction.getFkLoanTypeId_n());

                hrsPayrollReceiptDeduction.setReceiptDeduction(payrollReceiptDeduction);
                hrsPayrollReceiptDeduction.setPkMoveId(move);
                hrsPayrollReceiptDeduction.setHrsReceipt(hrsPayrollReceipt);
                aHrsPayrollReceiptDeductions.add(hrsPayrollReceiptDeduction);

                move++;
            }

            if (moPayroll.isNormal()) {
                // Get loans employee:

                for (SDbLoan loan : hrsPayrollReceipt.getHrsEmployee().getLoans()) {
                    hrsPayrollReceiptDeduction = new SHrsPayrollReceiptDeduction();

                    if (SLibTimeUtils.isBelongingToPeriod(loan.getDateStart(), loan.getDateEnd_n() == null ? dateEnd : loan.getDateEnd_n(), dateStart, dateEnd)) {
                        for (SDbDeduction deduction : maDeductions) {
                            if (deduction.getFkLoanTypeId() == loan.getFkLoanTypeId()) {
                                hrsPayrollReceiptDeduction.setDeduction(deduction);
                                break;
                            }
                        }

                        amountLoan = SHrsUtils.computeAmoutLoan(hrsPayrollReceipt, loan);

                        if (SLibUtils.belongsTo(loan.getFkLoanTypeId(), new int[] { SModSysConsts.HRSS_TP_LOAN_LOA_COM, SModSysConsts.HRSS_TP_LOAN_LOA_UNI, SModSysConsts.HRSS_TP_LOAN_LOA_TPS })) {
                            balanceLoan = SHrsUtils.getBalanceLoan(loan, hrsPayrollReceipt.getHrsEmployee());

                            amountLoan = (amountLoan > balanceLoan ? balanceLoan : amountLoan);
                        }

                        if (amountLoan > 0) {
                            if (hrsPayrollReceiptDeduction.getDeduction() == null) {
                                throw new Exception("No se encontró ninguna deducción para agregar el préstamo '" + loan.getLoanIdentificator() + "'.");
                            }
                            else {
                                payrollReceiptDeduction = createHrsPayrollReceiptDeduction(employeeId, 1, amountLoan, amountLoan,
                                        hrsPayrollReceiptDeduction.getDeduction(), loan.getPkEmployeeId(), loan.getPkLoanId(),
                                        loan.getFkLoanTypeId());

                                payrollReceiptDeduction.setUserEdited(false);
                                hrsPayrollReceiptDeduction.setReceiptDeduction(payrollReceiptDeduction);
                                hrsPayrollReceiptDeduction.setPkMoveId(move);
                                hrsPayrollReceiptDeduction.setHrsReceipt(hrsPayrollReceipt);
                                aHrsPayrollReceiptDeductions.add(hrsPayrollReceiptDeduction);

                                move++;
                            }
                        }
                    }
                }
            }
        }
        catch (Exception e) {
            SLibUtils.showException(this, e);
        }
        return aHrsPayrollReceiptDeductions;
    }

    public SDbPayrollReceiptEarning createHrsPayrollReceiptEarning(final int employeeId, final double units, final double amount_unit, final double amount_r, final boolean isAutomatic,
            final SDbEarning earning, final int loanEmployeeId_n, final int loanLoanId_n, final int loanTypeId_n, final SHrsPayrollReceipt hrsPayrollReceipt) {
        SDbPayrollReceiptEarning payrollReceiptEarning = new SDbPayrollReceiptEarning();
        
        payrollReceiptEarning.setUnitsAlleged(units);
        payrollReceiptEarning.setUnits(SLibUtils.round(!earning.isDaysAdjustment() ? units * hrsPayrollReceipt.getHrsEmployee().getEmployeeDays().getFactorCalendar() : (units * hrsPayrollReceipt.getHrsEmployee().getEmployeeDays().getFactorCalendar() * hrsPayrollReceipt.getHrsEmployee().getEmployeeDays().getFactorDaysPaid()), SLibUtils.DecimalFormatValue8D.getMaximumFractionDigits()));
        payrollReceiptEarning.setFactorAmount(1);
        payrollReceiptEarning.setAmountUnitary(amount_unit);
        payrollReceiptEarning.setAmountSystem_r(amount_r);
        payrollReceiptEarning.setAmount_r(amount_r);
        payrollReceiptEarning.setAutomatic(isAutomatic);
        payrollReceiptEarning.setAlternativeTaxCalculation(earning.isAlternativeTaxCalculation());// XXX (jbarajas, 2016-04-06) articule 174 RLISR
        payrollReceiptEarning.setPkEmployeeId(employeeId);
        payrollReceiptEarning.setFkEarningTypeId(earning.getFkEarningTypeId());
        payrollReceiptEarning.setFkEarningId(earning.getPkEarningId());
        payrollReceiptEarning.setFkBenefitTypeId(earning.getFkBenefitTypeId());
        payrollReceiptEarning.setFkLoanEmployeeId_n(loanEmployeeId_n);
        payrollReceiptEarning.setFkLoanLoanId_n(loanLoanId_n);
        payrollReceiptEarning.setFkLoanTypeId_n(loanTypeId_n);

        return payrollReceiptEarning;
    }
    
    public SDbPayrollReceiptEarning createHrsPayrollReceiptEarning( final SHrsPayrollReceipt hrsPayrollReceipt, final SHrsBenefit hrsBenefit, final double unitAlleged, final double pAmount_unt, final boolean isAutomatic,
            final SDbEarning earning, final int loanEmployeeId_n, final int loanLoanId_n, final int loanTypeId_n, final int moveId) {
        double unit = 0;
        double amount_unt = 0;
        double amount = 0;
        SDbPayrollReceiptEarning payrollReceiptEarning = new SDbPayrollReceiptEarning();
        
        if (earning.getFkEarningComputationTypeId() == SModSysConsts.HRSS_TP_EAR_COMP_AMT) {
            amount_unt = pAmount_unt;
        }
        else if (earning.getFkEarningComputationTypeId() == SModSysConsts.HRSS_TP_EAR_COMP_DAY) {
            amount_unt = hrsPayrollReceipt.getReceipt().getPaymentDaily();
        }
        else if (earning.getFkEarningComputationTypeId() == SModSysConsts.HRSS_TP_EAR_COMP_HRS) {
            amount_unt = hrsPayrollReceipt.getReceipt().getPaymentHourly();
        }
        else if (earning.getFkEarningComputationTypeId() == SModSysConsts.HRSS_TP_EAR_COMP_PER_DAY) {
            amount_unt = hrsPayrollReceipt.getReceipt().getPaymentDaily() * earning.getPayPercentage();
        }
        else if (earning.getFkEarningComputationTypeId() == SModSysConsts.HRSS_TP_EAR_COMP_PER_HRS) {
            amount_unt = hrsPayrollReceipt.getReceipt().getPaymentHourly() * earning.getPayPercentage();
        }
        else if (earning.getFkEarningComputationTypeId() == SModSysConsts.HRSS_TP_EAR_COMP_PER_EAR) {
            amount_unt = hrsPayrollReceipt.getTotalEarningsDependentsDaysWorked() * earning.getPayPercentage();
        }
        unit = SLibUtils.round(!earning.isDaysAdjustment() ? unitAlleged * hrsPayrollReceipt.getHrsEmployee().getEmployeeDays().getFactorCalendar() : (unitAlleged * hrsPayrollReceipt.getHrsEmployee().getEmployeeDays().getFactorCalendar() * hrsPayrollReceipt.getHrsEmployee().getEmployeeDays().getFactorDaysPaid()), SLibUtils.DecimalFormatValue8D.getMaximumFractionDigits());
        amount = SLibUtils.round((unit * amount_unt * earning.getUnitsFactor()), SLibUtils.DecimalFormatValue2D.getMaximumFractionDigits());
                
        payrollReceiptEarning.setPkPayrollId(hrsPayrollReceipt.getReceipt().getPkPayrollId());
        payrollReceiptEarning.setPkEmployeeId(hrsPayrollReceipt.getHrsEmployee().getEmployee().getPkEmployeeId());
        payrollReceiptEarning.setPkMoveId(moveId);
        payrollReceiptEarning.setUnitsAlleged(unitAlleged);
        payrollReceiptEarning.setUnits(unit);
        payrollReceiptEarning.setAlternativeTaxCalculation(earning.isAlternativeTaxCalculation());// XXX (jbarajas, 2016-04-06) articule 174 RLISR
        /*
        payrollReceiptEarning.setAmountExempt(double d);
        payrollReceiptEarning.setAmountTaxable(double d);
        payrollReceiptEarning.setUserEdited(boolean b);
        payrollReceiptEarning.setDeleted(boolean b);
        payrollReceiptEarning.setSystem(boolean b);
        */
        payrollReceiptEarning.setFkEarningTypeId(earning.getFkEarningTypeId());
        payrollReceiptEarning.setFkEarningId(earning.getPkEarningId());
        payrollReceiptEarning.setFkLoanEmployeeId_n(loanEmployeeId_n);
        payrollReceiptEarning.setFkLoanLoanId_n(loanLoanId_n);
        payrollReceiptEarning.setFkLoanTypeId_n(loanTypeId_n);
        
        if (hrsBenefit == null) {
            payrollReceiptEarning.setFactorAmount(earning.getUnitsFactor());
            payrollReceiptEarning.setAmountUnitary(amount_unt);
            payrollReceiptEarning.setAmountSystem_r(amount);
            payrollReceiptEarning.setAmount_r(amount);
            payrollReceiptEarning.setFkBenefitTypeId(earning.getFkBenefitTypeId());
            payrollReceiptEarning.setBenefitYear(0);
            payrollReceiptEarning.setBenefitAniversary(0);
        }
        else {
            payrollReceiptEarning.setFactorAmount(hrsBenefit.getFactorAmount());
            payrollReceiptEarning.setAmountUnitary(hrsPayrollReceipt.getReceipt().getPaymentDaily());
            payrollReceiptEarning.setAmountSystem_r(hrsBenefit.getAmountPayedReceiptSys());
            payrollReceiptEarning.setAmount_r(hrsBenefit.getAmountPayedReceipt());
            payrollReceiptEarning.setFkBenefitTypeId(hrsBenefit.getBenefitTypeId());
            payrollReceiptEarning.setBenefitYear(hrsBenefit.getBenefitYear());
            payrollReceiptEarning.setBenefitAniversary(hrsBenefit.getBenefitAnn());
        }
        payrollReceiptEarning.setAutomatic(isAutomatic);
        /*
        payrollReceiptEarning.setFkUserInsertId(int n);
        payrollReceiptEarning.setFkUserUpdateId(int n);
        payrollReceiptEarning.setTsUserInsert(Date t);
        payrollReceiptEarning.setTsUserUpdate(Date t);
        */

        return payrollReceiptEarning;
    }

    private SDbPayrollReceiptDeduction createHrsPayrollReceiptDeduction(final int employeeId, final double units, final double amount_unit, final double amount_r,
            final SDbDeduction deduction, final int loanEmployeeId_n, final int loanLoanId_n, final int loanTypeId_n) {
        SDbPayrollReceiptDeduction payrollReceiptDeduction = new SDbPayrollReceiptDeduction();

        payrollReceiptDeduction.setUnitsAlleged(units);
        payrollReceiptDeduction.setUnits(units);
        payrollReceiptDeduction.setAmountUnitary(amount_unit);
        payrollReceiptDeduction.setAmount_r(amount_r);
        payrollReceiptDeduction.setAutomatic(true);
        payrollReceiptDeduction.setPkEmployeeId(employeeId);
        payrollReceiptDeduction.setFkDeductionTypeId(deduction.getFkDeductionTypeId());
        payrollReceiptDeduction.setFkDeductionId(deduction.getPkDeductionId());
        payrollReceiptDeduction.setFkBenefitTypeId(deduction.getFkBenefitTypeId());
        payrollReceiptDeduction.setFkLoanEmployeeId_n(loanEmployeeId_n);
        payrollReceiptDeduction.setFkLoanLoanId_n(loanLoanId_n);
        payrollReceiptDeduction.setFkLoanTypeId_n(loanTypeId_n);

        return payrollReceiptDeduction;
    }

    /*
     * Public methods:
     */

    public void setConfig(SDbConfig o) { moConfig = o; }
    public void setWorkingDaySettings(SDbWorkingDaySettings o) { moWorkingDaySettings = o; }
    public void setPayroll(SDbPayroll o) { moPayroll = o; }
    public void setPayrollDataProvider(SHrsPayrollDataProvider o) { moPayrollDataProvider = o; }
    
    public void setLastPayrollPeriod(boolean b) { mbIsLastPayrollPeriod = b; }

    public SDbConfig getConfig() { return moConfig; }
    public SDbWorkingDaySettings getWorkingDaySettings() { return moWorkingDaySettings; }
    public SDbPayroll getPayroll() { return moPayroll; }

    public ArrayList<SDbLoanTypeAdjustment> getLoanTypeAdjustment() { return maLoanTypeAdjustment; }
    public ArrayList<SDbUma> getUmas() { return maUmas; }
    public ArrayList<SDbHoliday> getHolidays() { return maHolidays; }
    public ArrayList<SDbTaxTable> getTaxTables() { return maTaxTables; }
    public ArrayList<SDbTaxSubsidyTable> getTaxSubsidyTables() { return maTaxSubsidyTables; }
    public ArrayList<SDbSsContributionTable> getSsContributionTables() { return maSsContributionTables; }
    public ArrayList<SDbBenefitTable> getBenefitTables() { return maBenefitTables; }
    public ArrayList<SHrsBenefitTableByAnniversary> getBenefitTablesAnniversarys() { return maBenefitTablesAnniversarys; }
    public ArrayList<SDbMwzTypeWage> getMwzTypeWages() { return maMwzTypeWages; }
    public ArrayList<SDbEarning> getEarnigs() { return maEarnigs; }
    public ArrayList<SDbDeduction> getDeductions() { return maDeductions; }
    public ArrayList<SDbAutomaticEarning> getAutomaticEarnings() { return maAutomaticEarnings; }
    public ArrayList<SDbAutomaticDeduction> getAutomaticDeductions() { return maAutomaticDeductions; }
    public ArrayList<SDbEmployee> getEmployees() { return maEmployees; }
    public ArrayList<SHrsPayrollReceipt> getReceipts() { return maReceipts; }
    
    public boolean isLastPayrollPeriod() { return mbIsLastPayrollPeriod; }
    
    public double getLoanTypeAdjustment(final Date date, final int loanType) {
        double amount = 0;

        for (SDbLoanTypeAdjustment adjustment : maLoanTypeAdjustment) {
            if (!date.before(adjustment.getDateStart()) && adjustment.getPkLoanTypeId() == loanType) {
                amount = adjustment.getAdjustment();
                break;
            }
        }

        return amount;
    }
    
    /**
     * Obtain amount UMA most appropriate for date indicated.
     * @param date Date for required UMA.
     * @return 
     */
    public double getUma(final Date date) {
        double amount = 0;

        for (SDbUma adjustment : maUmas) {
            if (!date.before(adjustment.getDateStart())) {
                amount = adjustment.getAmount();
                break;
            }
        }

        return amount;
    }
    
    public ArrayList<SDbHoliday> getHolidays(final Date dateStart, final Date dateEnd) {
        ArrayList<SDbHoliday> aHolidays = new ArrayList<SDbHoliday>();

        for (SDbHoliday holiday : maHolidays) {
            if (holiday.getDate().after(dateStart) && holiday.getDate().before(dateEnd)) {
                aHolidays.add(holiday);
            }
        }

        return aHolidays;
    }


    public SDbTaxTable getTaxTable(final int tableId) {
        SDbTaxTable taxTable = null;

        for (SDbTaxTable table : maTaxTables) {
            if (table.getPkTaxTableId() == tableId) {
                taxTable = table;
                break;
            }
        }

        return taxTable;
    }

    public SDbTaxTable getTaxTableByDate(final Date date) {
        SDbTaxTable taxTable = null;

        for (SDbTaxTable table : maTaxTables) {
            if (!date.before(table.getDateStart())) {
                taxTable = table;
                break;
            }
        }

        return taxTable;
    }

    public SDbTaxSubsidyTable getTaxSubsidyTable(final int tableId) {
        SDbTaxSubsidyTable taxSubsidyTable = null;

        for (SDbTaxSubsidyTable table : maTaxSubsidyTables) {
            if (table.getPkTaxSubsidyTableId() == tableId) {
                taxSubsidyTable = table;
                break;
            }
        }

        return taxSubsidyTable;
    }

    public SDbTaxSubsidyTable getTaxSubsidyTableByDate(final Date date) {
        SDbTaxSubsidyTable taxSubsidyTable = null;

        for (SDbTaxSubsidyTable table : maTaxSubsidyTables) {
            if (!date.before(table.getDateStart())) {
                taxSubsidyTable = table;
                break;
            }
        }

        return taxSubsidyTable;
    }

    public SDbSsContributionTable getSsContributionTable(final int tableId) {
        SDbSsContributionTable ssContributionTable = null;

        for (SDbSsContributionTable table : maSsContributionTables) {
            if (table.getPkSsContributionTableId() == tableId) {
                ssContributionTable = table;
                break;
            }
        }

        return ssContributionTable;
    }

    public SDbSsContributionTable getSsContributionTableByDate(final Date date) {
        SDbSsContributionTable ssContributionTable = null;

        for (SDbSsContributionTable table : maSsContributionTables) {
            if (!date.before(table.getDateStart())) {
                ssContributionTable = table;
                break;
            }
        }

        return ssContributionTable;
    }
    
    public SDbBenefitTable getBenefitTable(final int tableId) {
        SDbBenefitTable benefitTable = null;

        for (SDbBenefitTable table : maBenefitTables) {
            if (table.getPkBenefitId() == tableId) {
                benefitTable = table;
                break;
            }
        }

        return benefitTable;
    }
    
    public SDbBenefitTable getBenefitTable(final Date date, final int benefitType) {
        SDbBenefitTable benefitTable = null;

        for (SDbBenefitTable table : maBenefitTables) {
            if (table.getFkBenefitTypeId() == benefitType &&
                    !date.before(table.getDateStart())) {
                benefitTable = table;
                break;
            }
        }

        return benefitTable;
    }
    
    public ArrayList<SHrsBenefitTableByAnniversary> getBenefitTableAnniversary(final int tableId) {
        ArrayList<SHrsBenefitTableByAnniversary> aBenefitTableByAnniversary = new ArrayList<SHrsBenefitTableByAnniversary>();

        for (SHrsBenefitTableByAnniversary table : maBenefitTablesAnniversarys) {
            if (table.getBenefitId() == tableId) {
                aBenefitTableByAnniversary.add(table);
            }
        }

        return aBenefitTableByAnniversary;
    }

    public SDbMwzTypeWage getMwzTypeWage(final int mwzTypeId, final int wageId) {
        SDbMwzTypeWage mwzTypeWage = null;

        for (SDbMwzTypeWage wage : maMwzTypeWages) {
            if (wage.getPkMwzTypeId() == mwzTypeId && wage.getPkWageId() == wageId) {
                mwzTypeWage = wage;
                break;
            }
        }

        return mwzTypeWage;
    }

    public SDbMwzTypeWage getMwzTypeWageByDate(final int mwzTypeId, final Date date) {
        SDbMwzTypeWage oMwzTypeWage = null;

        for (SDbMwzTypeWage mwzTypeWage : maMwzTypeWages) {
            if (mwzTypeWage.getPkMwzTypeId() == mwzTypeId && !date.before(mwzTypeWage.getDateStart())) {
                oMwzTypeWage = mwzTypeWage;
                break;
            }
        }

        return oMwzTypeWage;
    }

    public SDbEarning getDataEarning(final int id) {
        SDbEarning earning = null;

        for (SDbEarning entry : maEarnigs) {
            if (entry.getPkEarningId() == id) {
                earning = entry;
                break;
            }
        }

        return earning;
    }

    public SDbEarning getDataEarningByType(final int type) {
        SDbEarning earning = null;

        for (SDbEarning entry : maEarnigs) {
            if (entry.getFkEarningTypeId() == type) {
                earning = entry;
                break;
            }
        }

        return earning;
    }

    public SDbDeduction getDataDeduction(final int id) {
        SDbDeduction deduction = null;

        for (SDbDeduction entry : maDeductions) {
            if (entry.getPkDeductionId() == id) {
                deduction = entry;
                break;
            }
        }

        return deduction;
    }

    public SDbDeduction getDataDeductionByType(final int type) {
        SDbDeduction deduction = null;

        for (SDbDeduction entry : maDeductions) {
            if (entry.getFkDeductionTypeId() == type) {
                deduction = entry;
                break;
            }
        }

        return deduction;
    }

    public SDbEmployee getDataEmployee(final int id) {
        SDbEmployee employee = null;

        for (SDbEmployee entry : maEmployees) {
            if (entry.getPkEmployeeId() == id) {
                employee = entry;
                break;
            }
        }

        return employee;
    }

    public ArrayList<SDbAutomaticEarning> getAutomaticEarnings(final int employeeId, final Date dateStart, final Date dateEnd, final int paysheetTypeId) {
        ArrayList<SDbAutomaticEarning> aAutomaticEarnings = new ArrayList<>();

        for (SDbAutomaticEarning automaticEarning : maAutomaticEarnings) {
            if ((automaticEarning.getFkEmployeeId_n() == SLibConsts.UNDEFINED ||
                    automaticEarning.getFkEmployeeId_n() == employeeId) &&
                    (SLibTimeUtils.isBelongingToPeriod(automaticEarning.getDateStart(), automaticEarning.getDateEnd_n() == null ? dateEnd : automaticEarning.getDateEnd_n(), dateStart, dateEnd)) &&
                    automaticEarning.getFkPaysheetTypeId() == paysheetTypeId) {
                aAutomaticEarnings.add(automaticEarning);
            }
        }

        return aAutomaticEarnings;
    }

    public ArrayList<SDbAutomaticDeduction> getAutomaticDeductions(final int employeeId, final Date dateStart, final Date dateEnd, final int paysheetTypeId) {
        ArrayList<SDbAutomaticDeduction> aAutomaticDeductions = new ArrayList<>();

        for (SDbAutomaticDeduction automaticDeduction : maAutomaticDeductions) {
            if ((automaticDeduction.getFkEmployeeId_n() == SLibConsts.UNDEFINED ||
                    automaticDeduction.getFkEmployeeId_n() == employeeId) &&
                    (SLibTimeUtils.isBelongingToPeriod(automaticDeduction.getDateStart(), automaticDeduction.getDateEnd_n() == null ? dateEnd : automaticDeduction.getDateEnd_n(), dateStart, dateEnd)) &&
                    automaticDeduction.getFkPaysheetTypeId() == paysheetTypeId) {
                aAutomaticDeductions.add(automaticDeduction);
            }
        }

        return aAutomaticDeductions;
    }

    public SHrsPayrollReceipt getReceipt(final int employeeId) {
        SHrsPayrollReceipt oHrsPayrollReceipt = null;

        for (SHrsPayrollReceipt hrsPayrollReceipt : maReceipts) {
            if (hrsPayrollReceipt.getHrsEmployee().getEmployee().getPkEmployeeId() == employeeId) {
                oHrsPayrollReceipt = hrsPayrollReceipt;
            }
        }

        return oHrsPayrollReceipt;
    }

    public SHrsPayrollReceipt createReceipt(final int employeeId, int payrollYear, int payrollYearPeriod, int fiscalYear, Date dateStart, Date dateEnd, final int taxComputationType) throws Exception {
        SDbPayrollReceipt payrollReceipt = null;
        SHrsEmployee oHrsEmployee = null;
        SHrsPayrollReceipt oHrsPayrollReceipt = new SHrsPayrollReceipt();
        
        oHrsPayrollReceipt.setHrsPayroll(this);
        
        // Get receipt employee days:
        oHrsEmployee = moPayrollDataProvider.createEmployee(this, moPayroll.getPkPayrollId(), employeeId, payrollYear, payrollYearPeriod, fiscalYear, dateStart, dateEnd, taxComputationType);
        oHrsEmployee.setHrsPayrollReceipt(oHrsPayrollReceipt);
        oHrsPayrollReceipt.setHrsEmployee(oHrsEmployee);
        
        // Create payrollReceipt:
        payrollReceipt = createPayrollReceipt(oHrsEmployee);
        
        oHrsPayrollReceipt.setReceipt(payrollReceipt);

        if (moPayroll.isNormal()) {
            // Get absence consumption:
            oHrsPayrollReceipt.getAbsenceConsumptions().addAll(getAbsenceConsumptionDays(oHrsPayrollReceipt, oHrsEmployee));
        }
        
        // Get earnings:
        oHrsPayrollReceipt.getHrsEarnings().addAll(getPayrollReceiptEarnings(oHrsPayrollReceipt, employeeId, dateStart, dateEnd));
        
        // Compute payrollReceiptDays:
        oHrsPayrollReceipt.computeDbPayrollReceiptDays();

        // Get deductions:
        oHrsPayrollReceipt.getHrsDeductions().addAll(getPayrollReceiptDeductions(oHrsPayrollReceipt, employeeId, dateStart, dateEnd));

        // Compute payrollReceipt:
        oHrsPayrollReceipt.computeReceipt();

        // Add hrsReceipt:
        maReceipts.add(oHrsPayrollReceipt);
        
        oHrsPayrollReceipt.renumberEarnings();
        oHrsPayrollReceipt.renumberDeductions();

        return oHrsPayrollReceipt;
    }

    public void replaceReceipt(final int employeeId, final SHrsPayrollReceipt receipt, final boolean compute) {
        SHrsPayrollReceipt hrsPayrollReceipt = null;

        for (int i = 0; i < maReceipts.size(); i++) {
            hrsPayrollReceipt = maReceipts.get(i);

            if (hrsPayrollReceipt.getReceipt().getPkEmployeeId() == employeeId) {
                maReceipts.set(i, receipt);
                break;
            }
        }
        //computeReceipts(); XXX jbarajas; only calculate receipt value 08-09-2015
        if (compute) {
            try {
                receipt.computeReceipt();
            }
            catch (Exception e) {
                SLibUtils.showException(this, e);
            }
        }
    }

    public void removeReceipt(final int employeeId) {
        SHrsPayrollReceipt hrsPayrollReceipt = null;

        for (int i = 0; i < maReceipts.size(); i++) {
            hrsPayrollReceipt = maReceipts.get(i);

            if (hrsPayrollReceipt.getReceipt().getPkEmployeeId() == employeeId) {
                maReceipts.remove(i);
                break;
            }
        }
        //computeReceipts(); XXX jbarajas; only calculate receipt value 08-09-2015
    }

    public void computeEmployees(final boolean isCopy) {
        try {
            for (SHrsPayrollReceipt receipt : maReceipts) {
                receipt.setHrsEmployee(moPayrollDataProvider.computeEmployee(receipt.getHrsEmployee(), isCopy ? SLibConsts.UNDEFINED : moPayroll.getPkPayrollId(), receipt.getHrsEmployee().getEmployee().getPkEmployeeId(),
                        moPayroll.getPeriodYear(), moPayroll.getPeriod(), moPayroll.getFiscalYear(), moPayroll.getDateStart(), moPayroll.getDateEnd(), moPayroll.getFkTaxComputationTypeId()));
            }
        }
        catch (Exception e) {
            SLibUtils.showException(this, e);
        }
    }

    public void computeReceipts() {
        try {
            for (SHrsPayrollReceipt receipt : maReceipts) {
                receipt.computeReceipt();
            }
        }
        catch (Exception e) {
            SLibUtils.showException(this, e);
        }
    }

    public double getTotalEarnings() {
        double totalEarnings = 0;

        for (SHrsPayrollReceipt hrsPayrollReceipt : maReceipts) {
            for (SHrsPayrollReceiptEarning hrsPayrollReceiptEarning : hrsPayrollReceipt.getHrsEarnings()) {

                totalEarnings += hrsPayrollReceiptEarning.getReceiptEarning().getAmount_r();
            }
        }

        return totalEarnings;
    }

    public double getTotalDeductions() {
        double totalDeductions = 0;

        for (SHrsPayrollReceipt hrsPayrollReceipt : maReceipts) {
            for (SHrsPayrollReceiptDeduction hrsPayrollReceiptDeduction : hrsPayrollReceipt.getHrsDeductions()) {

                totalDeductions += hrsPayrollReceiptDeduction.getReceiptDeduction().getAmount_r();
            }
        }

        return totalDeductions;
    }

    public SDbPayrollReceipt createPayrollReceipt(final SHrsEmployee hrsEmployee) throws Exception {
        SDbPayrollReceipt payrollReceipt = null;

        // Create dbReceipt:

        payrollReceipt = new SDbPayrollReceipt();
        payrollReceipt.setPkEmployeeId(hrsEmployee.getEmployee().getPkEmployeeId());
        payrollReceipt.setDateBenefits(hrsEmployee.getEmployee().getDateBenefits());
        payrollReceipt.setDateLastHire(hrsEmployee.getEmployee().getDateLastHire());
        payrollReceipt.setDateLastDismiss_n(hrsEmployee.getEmployee().getDateLastDismiss_n());
        payrollReceipt.setSalary(hrsEmployee.getEmployee().getSalary());
        payrollReceipt.setWage(hrsEmployee.getEmployee().getWage());
        payrollReceipt.setSalarySscBase(hrsEmployee.getEmployee().getSalarySscBase());
        payrollReceipt.setWorkingHoursDay(hrsEmployee.getEmployee().getWorkingHoursDay());
        //payrollReceipt.setBankAccount(hrsEmployee.getEmployee().getBankAccount()); XXX (jbarajas, 2015-10-07) remove by new table
        // XXX modify when payment for fornight is fijo
        payrollReceipt.setPaymentDaily(hrsEmployee.getEmployee().getFkPaymentTypeId() == SModSysConsts.HRSS_TP_PAY_WEE ? hrsEmployee.getEmployee().getSalary() :
                (moConfig.isFornightStandard() ? ((hrsEmployee.getEmployee().getWage() * SHrsConsts.YEAR_MONTHS) / (SHrsConsts.FORNIGHT_FIXED_DAYS * SHrsConsts.YEAR_FORNIGHTS)) :
                ((hrsEmployee.getEmployee().getWage() * SHrsConsts.YEAR_MONTHS) / SHrsConsts.YEAR_DAYS)));
        payrollReceipt.setPaymentHourly(hrsEmployee.getEmployee().getWorkingHoursDay() > 0 ? payrollReceipt.getPaymentDaily() / hrsEmployee.getEmployee().getWorkingHoursDay() : 0);
        payrollReceipt.setFkPaymentTypeId(hrsEmployee.getEmployee().getFkPaymentTypeId());
        payrollReceipt.setFkSalaryTypeId(hrsEmployee.getEmployee().getFkSalaryTypeId());
        payrollReceipt.setFkEmployeeTypeId(hrsEmployee.getEmployee().getFkEmployeeTypeId());
        payrollReceipt.setFkWorkerTypeId(hrsEmployee.getEmployee().getFkWorkerTypeId());
        payrollReceipt.setFkMwzTypeId(hrsEmployee.getEmployee().getFkMwzTypeId());
        payrollReceipt.setFkDepartmentId(hrsEmployee.getEmployee().getFkDepartmentId());
        payrollReceipt.setFkPositionId(hrsEmployee.getEmployee().getFkPositionId());
        payrollReceipt.setFkShiftId(hrsEmployee.getEmployee().getFkShiftId());
        payrollReceipt.setFkContractTypeId(hrsEmployee.getEmployee().getFkContractTypeId());
        payrollReceipt.setFkRecruitmentSchemeTypeId(hrsEmployee.getEmployee().getFkRecruitmentSchemeTypeId());
        payrollReceipt.setFkPositionRiskTypeId(hrsEmployee.getEmployee().getFkPositionRiskTypeId());
        payrollReceipt.setFkWorkingDayTypeId(hrsEmployee.getEmployee().getFkWorkingDayTypeId());
        payrollReceipt.setActive(hrsEmployee.getEmployee().isActive());
        hrsEmployee.getHrsPayrollReceipt().setReceipt(payrollReceipt);
        hrsEmployee.getHrsPayrollReceipt().setHrsPayroll(this);

        return payrollReceipt;
    }
    
    public ArrayList<SHrsPayrollReceiptEarning> getHrsPayrollReceiptEarningAbsence(final ArrayList<SDbAbsenceConsumption> aAbsenceConsumption, final SHrsPayrollReceipt hrsPayrollReceipt) throws Exception {
        int move = 0;
        int earningId = 0;
        double unit = 0;
        double unitAlleged = 0;
        double amount_unt = 0;
        double amount = 0;
        SDbPayrollReceiptEarning payrollReceiptEarning = null;
        SHrsPayrollReceiptEarning hrsPayrollReceiptEarning = null;
        ArrayList<SHrsPayrollReceiptEarning> aHrsPayrollReceiptEarnings = null;
        SDbEarning earning = null;
        
        aHrsPayrollReceiptEarnings = new ArrayList<SHrsPayrollReceiptEarning>();
        
        move = 1;
        for (SDbAbsenceConsumption receiptAbsenceConsumption : aAbsenceConsumption) {
            earning = null;
            
            for (SDbEarning earningAux : maEarnigs) {
                if (SLibUtils.compareKeys(new int[] { receiptAbsenceConsumption.getAbsence().getFkAbsenceClassId(), receiptAbsenceConsumption.getAbsence().getFkAbsenceTypeId() }, new int[] { earningAux.getFkAbsenceClassId_n(), earningAux.getFkAbsenceTypeId_n() })) {
                    earning = earningAux;
                    break;
                }
            }
            
            if (earning != null) {
                if (earning.getFkAbsenceClassId_n() == SModSysConsts.HRSU_CL_ABS_VAC) { // jbarajas 19/03/2015 validate the class of the absence, of the module configuration or of the type of absence
                    earningId = moConfig.getFkEarningVacationsId_n();
                }
                else {
                   earningId = earning.getPkEarningId();
                }
                
                if (earningId == SLibConsts.UNDEFINED) {
                    throw new Exception("No se encontró ninguna percepción configurada " +
                            (earning.getFkAbsenceClassId_n() == SModSysConsts.HRSU_CL_ABS_VAC ? " en la configuración del módulo para 'vacaciones'":
                            "en el catálogo de tipos de incidencias para el tipo '" + earning.getName()) + "'.");
                }
                unitAlleged = receiptAbsenceConsumption.getEffectiveDays();
                
                hrsPayrollReceiptEarning = new SHrsPayrollReceiptEarning();
                hrsPayrollReceiptEarning.setEarning(earning);
                
                
                if (earning.getFkEarningComputationTypeId() == SModSysConsts.HRSS_TP_EAR_COMP_AMT) {
                    amount_unt = unitAlleged;
                }
                /* XXX (jbarajas, 2016-09-12) concentrate in one place the calculation of perceptions and deductions.
                else if (earning.getFkEarningComputationTypeId() == SModSysConsts.HRSS_TP_EAR_COMP_DAY) {
                    amount_unt = hrsPayrollReceipt.getReceipt().getPaymentDaily();
                }
                else if (earning.getFkEarningComputationTypeId() == SModSysConsts.HRSS_TP_EAR_COMP_HRS) {
                    amount_unt = hrsPayrollReceipt.getReceipt().getPaymentHourly();
                }
                else if (earning.getFkEarningComputationTypeId() == SModSysConsts.HRSS_TP_EAR_COMP_PER_DAY) {
                    amount_unt = hrsPayrollReceipt.getReceipt().getPaymentDaily() * earning.getPayPercentage();
                }
                else if (earning.getFkEarningComputationTypeId() == SModSysConsts.HRSS_TP_EAR_COMP_PER_HRS) {
                    amount_unt = hrsPayrollReceipt.getReceipt().getPaymentHourly() * earning.getPayPercentage();
                }
                unit = !earning.isDaysAdjustment() ? unitAlleged * hrsPayrollReceipt.getHrsEmployee().getEmployeeDays().getFactorCalendar() : (unitAlleged * hrsPayrollReceipt.getHrsEmployee().getEmployeeDays().getFactorCalendar() * hrsPayrollReceipt.getHrsEmployee().getEmployeeDays().getFactorDaysPaid());
                amount = SLibUtils.round((unit * amount_unt), SLibUtils.DecimalFormatValue2D.getMaximumFractionDigits());

                payrollReceiptEarning = createHrsPayrollReceiptEarning(hrsPayrollReceipt.getHrsEmployee().getEmployee().getPkEmployeeId(), unitAlleged, amount_unt, amount, false, earning,
                    SLibConsts.UNDEFINED, SLibConsts.UNDEFINED, earning.getFkLoanTypeId(), hrsPayrollReceipt);
                */

                payrollReceiptEarning = createHrsPayrollReceiptEarning(hrsPayrollReceipt, null, unitAlleged, amount_unt, false, earning, 
                            SLibConsts.UNDEFINED, SLibConsts.UNDEFINED, earning.getFkLoanTypeId(), move);
                
                payrollReceiptEarning.setSystem(true);
                if (earning.getFkBenefitTypeId() != SModSysConsts.HRSS_TP_BEN_NON) {
                    payrollReceiptEarning.setFactorAmount(1);
                    payrollReceiptEarning.setBenefitAniversary(receiptAbsenceConsumption.getAbsence().getBenefitsAniversary());
                    payrollReceiptEarning.setBenefitYear(receiptAbsenceConsumption.getAbsence().getBenefitsYear());
                }
                hrsPayrollReceiptEarning.setReceiptEarning(payrollReceiptEarning);
                hrsPayrollReceiptEarning.setPkMoveId(move);
                hrsPayrollReceiptEarning.setHrsReceipt(hrsPayrollReceipt);
                aHrsPayrollReceiptEarnings.add(hrsPayrollReceiptEarning);
            }
            move++;
        }
        
        return aHrsPayrollReceiptEarnings;
    }

    public double getFactorDaysPaid() {
        return moPayroll.getWorkingDays() == 0 ? 0 : (double) moPayroll.getReceiptDays() / moPayroll.getWorkingDays();
    }
}
