package com.afm.Reports.pages;

import java.util.concurrent.TimeUnit;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import com.afm.base.BaseLibrary;
import com.afm.constants.AfmCommonConstants;
import com.afm.constants.AfmNewOrderConstants;
import com.afm.helper.CommonActions;
import com.afm.helper.ReadConfig;
import com.afm.helper.WaitUtility;

public class AccountReportPage extends BaseLibrary {
	public static Logger logger = LogManager.getLogger(AccountReportPage.class);
	public String orderNumber;
	public String tripNumber;
	public String driverName;
	public int sizeNumber;
	public int randomNumber;
	public String name;
	public String carrierName;
	public String customerName;
	public String salesName;
	public String truckName;
	public String driverTwoName;
	public String trailerName;
	public String trailerReferenceName;
	public String driverTwoReferenceName;
	public String paymentReferenceNumber;
	public String truckReferenceName;
	public String driverReferenceName;
	public String vendorName;
	public String vendorNameRandomNum;
	ReadConfig readConfig = new ReadConfig();
	String env = readConfig.getPropertiesValue("baseURL");

	/**
	 * Constructor to initialize the PageFactory
	 */
	public AccountReportPage() {
		PageFactory.initElements(driver, this);
	}

	/**
	 * Reports-->Account-->Profit & Loss Report Create
	 */
	public boolean profitAndLossReport() {

		try {
			logger.info("Profit And Loss Report Create");
			String dropFutureDate = CommonActions.getFutureDate(AfmCommonConstants.FIVE).concat(" 12:00 AM");
			CommonActions.clickOnElement(AfmNewOrderConstants.REPORTS_XPATH);
			CommonActions.clickOnElement(AfmNewOrderConstants.ACCOUNT_XPATH);
			CommonActions.clickOnElement(AfmNewOrderConstants.PROFIT_LOSS_XPATH);
			CommonActions.clickOnElement(AfmNewOrderConstants.PROFIT_ADVANCE_SEARCH_XPATH);
			CommonActions.selectByVisibleText(AfmNewOrderConstants.PROFIT_CURRENCY_XPATH,
					AfmNewOrderConstants.CURRENCY_VALUE_CAD);
			CommonActions.clickAndClearText(AfmNewOrderConstants.PROFIT_DATE_XPATH);
			CommonActions.clickAndEnterText(AfmNewOrderConstants.PROFIT_DATE_XPATH, dropFutureDate);
			boolean isSubmitButtonDisplayed = CommonActions.verifyElementDisplayed(AfmNewOrderConstants.SUBMIT_XPATH);
			CommonActions.clickOnElement(AfmNewOrderConstants.SUBMIT_XPATH);
			return isSubmitButtonDisplayed;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * Reports-->Account-->Profit & Loss Report Print
	 */
	public boolean profitAndLossReportPrint() {

		try {
			logger.info("Profit And Loss Report Print");
			driver.manage().timeouts().implicitlyWait(2, TimeUnit.MINUTES);
			CommonActions.clickOnElement(AfmNewOrderConstants.REPORT_PRINT_XPATH);
			driver.manage().timeouts().implicitlyWait(2, TimeUnit.MINUTES);
			boolean isRefershButtonDisplayed = CommonActions
					.verifyElementDisplayed(AfmNewOrderConstants.REFERSH_REPORT_XPATH);
			CommonActions.clickOnElement(AfmNewOrderConstants.REFERSH_REPORT_XPATH);
			captureScreen(driver, "profitAndLossReportPrint");
			return isRefershButtonDisplayed;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * Reports-->Account-->Profit And Loss Report Email
	 */
	public boolean profitAndLossReportEmail() {

		try {
			logger.info("Profit And Loss Report Email");
			driver.manage().timeouts().implicitlyWait(2, TimeUnit.MINUTES);
			WebElement ele = driver.findElement(By.xpath("//*[contains(@onclick,'EmailAFMReports')]"));
			JavascriptExecutor js = (JavascriptExecutor) driver;
			driver.manage().timeouts().implicitlyWait(1, TimeUnit.MINUTES);
			js.executeScript("arguments[0].click();", ele);
			CommonActions.clickAndEnterText(AfmNewOrderConstants.EMAIL_TO_XPATH, AfmNewOrderConstants.EMAIL_TO_VALUE);
			boolean isEmailSendButtonDisplayed = CommonActions
					.verifyElementDisplayed(AfmNewOrderConstants.EMAIL_SEND_XPATH);
			CommonActions.clickOnElement(AfmNewOrderConstants.EMAIL_SEND_XPATH);
			captureScreen(driver, "profitAndLossReportEmail");
			WaitUtility.pause(AfmCommonConstants.THREE);
			return isEmailSendButtonDisplayed;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * Reports-->Account-->Cheque Register Report
	 */
	public boolean chequeRegisterReport() {

		try {
			logger.info("Cheque Register Report");
			CommonActions.clickOnElement(AfmNewOrderConstants.ACCOUNT_XPATH);
			String dropFutureDate = CommonActions.getFutureDate(AfmCommonConstants.FIVE).concat(" 12:00 AM");
			CommonActions.clickOnElement(AfmNewOrderConstants.CHEQUE_REGISTER_XPATH);
			CommonActions.clickOnElement(AfmNewOrderConstants.CHEQUE_REGISTER_ADVANCE_SEARCH_XPATH);
			CommonActions.clickAndClearText(AfmNewOrderConstants.CHEQUE_REGISTER_DATE_XPATH);
			CommonActions.clickAndEnterText(AfmNewOrderConstants.CHEQUE_REGISTER_DATE_XPATH, dropFutureDate);
			boolean isSubmitButtonDisplayed = CommonActions.verifyElementDisplayed(AfmNewOrderConstants.SUBMIT_XPATH);
			CommonActions.clickOnElement(AfmNewOrderConstants.SUBMIT_XPATH);
			return isSubmitButtonDisplayed;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * Reports-->Account-->Cheque Register Report Print
	 */
	public boolean chequeRegisterReportPrint() {

		try {
			logger.info("Profit And Loss Report Print");
			driver.manage().timeouts().implicitlyWait(2, TimeUnit.MINUTES);
			CommonActions.clickOnElement(AfmNewOrderConstants.REPORT_PRINT_XPATH);
			driver.manage().timeouts().implicitlyWait(2, TimeUnit.MINUTES);
			boolean isRefershButtonDisplayed = CommonActions
					.verifyElementDisplayed(AfmNewOrderConstants.REFERSH_REPORT_XPATH);
			CommonActions.clickOnElement(AfmNewOrderConstants.REFERSH_REPORT_XPATH);
			captureScreen(driver, "chequeRegisterReportPrint");
			return isRefershButtonDisplayed;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * Reports-->Account-->Profit And Loss Report Email
	 */
	public boolean chequeRegisterReportEmail() {

		try {
			logger.info("Cheque Register Report Email");
			driver.manage().timeouts().implicitlyWait(2, TimeUnit.MINUTES);
			WebElement ele = driver.findElement(By.xpath("//*[contains(@onclick,'EmailAFMReports')]"));
			JavascriptExecutor js = (JavascriptExecutor) driver;
			driver.manage().timeouts().implicitlyWait(1, TimeUnit.MINUTES);
			js.executeScript("arguments[0].click();", ele);
			CommonActions.clickAndEnterText(AfmNewOrderConstants.EMAIL_TO_XPATH, AfmNewOrderConstants.EMAIL_TO_VALUE);
			boolean isEmailSendButtonDisplayed = CommonActions
					.verifyElementDisplayed(AfmNewOrderConstants.EMAIL_SEND_XPATH);
			CommonActions.clickOnElement(AfmNewOrderConstants.EMAIL_SEND_XPATH);
			captureScreen(driver, "chequeRegisterReportEmail");
			WaitUtility.pause(AfmCommonConstants.THREE);
			return isEmailSendButtonDisplayed;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * Reports-->Account-->Bank Statement Report
	 */
	public boolean bankStatementReport() {

		try {
			logger.info("Bank Statement Report");
			String dropFutureDate = CommonActions.getFutureDate(AfmCommonConstants.FIVE).concat(" 12:00 AM");
			CommonActions.clickOnElement(AfmNewOrderConstants.ACCOUNT_XPATH);
			boolean isBankStatementButtonDisplayed = CommonActions
					.verifyElementDisplayed(AfmNewOrderConstants.BANK_STATEMENT_XPATH);
			CommonActions.clickOnElement(AfmNewOrderConstants.BANK_STATEMENT_XPATH);
			CommonActions.selectByVisibleText(AfmNewOrderConstants.BANK_ACCOUNT_XPATH, carrierName);
			CommonActions.clickAndClearText(AfmNewOrderConstants.BANK_STATEMENT_DATE_XPATH);
			CommonActions.clickAndEnterText(AfmNewOrderConstants.BANK_STATEMENT_DATE_XPATH, dropFutureDate);
			boolean isSubmitButtonDisplayed = CommonActions.verifyElementDisplayed(AfmNewOrderConstants.SUBMIT_XPATH);
			CommonActions.clickOnElement(AfmNewOrderConstants.SUBMIT_XPATH);
			captureScreen(driver, "bankStatementReport");
			return isBankStatementButtonDisplayed && isSubmitButtonDisplayed;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * Reports-->Account-->Bank Statement Report Print
	 */
	public boolean bankStatementReportPrint() {

		try {
			logger.info("Bank Statement Report Print");
			driver.manage().timeouts().implicitlyWait(2, TimeUnit.MINUTES);
			CommonActions.clickOnElement(AfmNewOrderConstants.REPORT_PRINT_XPATH);
			driver.manage().timeouts().implicitlyWait(2, TimeUnit.MINUTES);
			boolean isRefershButtonDisplayed = CommonActions
					.verifyElementDisplayed(AfmNewOrderConstants.REFERSH_REPORT_XPATH);
			CommonActions.clickOnElement(AfmNewOrderConstants.REFERSH_REPORT_XPATH);
			captureScreen(driver, "bankStatementReportPrint");
			return isRefershButtonDisplayed;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * Reports-->Account-->Bank Statement Report Email
	 */
	public boolean bankStatementReportEmail() {

		try {
			logger.info("Bank Statement Report Email");
			driver.manage().timeouts().implicitlyWait(2, TimeUnit.MINUTES);
			WebElement ele = driver.findElement(By.xpath("//*[contains(@onclick,'EmailAFMReports')]"));
			JavascriptExecutor js = (JavascriptExecutor) driver;
			driver.manage().timeouts().implicitlyWait(1, TimeUnit.MINUTES);
			js.executeScript("arguments[0].click();", ele);
			CommonActions.clickAndEnterText(AfmNewOrderConstants.EMAIL_TO_XPATH, AfmNewOrderConstants.EMAIL_TO_VALUE);
			boolean isEmailSendButtonDisplayed = CommonActions
					.verifyElementDisplayed(AfmNewOrderConstants.EMAIL_SEND_XPATH);
			CommonActions.clickOnElement(AfmNewOrderConstants.EMAIL_SEND_XPATH);
			captureScreen(driver, "bankStatementReportEmail");
			WaitUtility.pause(AfmCommonConstants.THREE);
			return isEmailSendButtonDisplayed;
		} catch (Exception e) {
			return false;
		}
	}
}
