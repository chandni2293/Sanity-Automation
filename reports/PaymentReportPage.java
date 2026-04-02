package com.afm.Reports.pages;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import com.afm.base.BaseLibrary;
import com.afm.constants.AfmAccountingConstants;
import com.afm.constants.AfmCommonConstants;
import com.afm.constants.AfmNewOrderConstants;
import com.afm.constants.AfmTripOutSourceConstants;
import com.afm.helper.CommonActions;
import com.afm.helper.ReadConfig;
import com.afm.helper.WaitUtility;

public class PaymentReportPage extends BaseLibrary {
	public static Logger logger = LogManager.getLogger(PaymentReportPage.class);
	public String orderNumber;
	public String tripNumber;
	public String driverName;
	public String truckOwnerPayment;
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
	public String truckOwnerReference;
	public String vendorName;
	public String vendorNameRandomNum;
	public String receiptNumber;
	public String discountTypeName;
	public String receiptDiscountTypeName;
	public int sizeNumber;
	public int randomNumber;

	ReadConfig readConfig = new ReadConfig();
	String env = readConfig.getPropertiesValue("baseURL");

	/**
	 * Constructor to initialize the PageFactory
	 */
	public PaymentReportPage() {
		PageFactory.initElements(driver, this);
	}

	/**
	 * Enter Customer Details For Regular Order
	 */
	public boolean enterCustomerDetails() throws InterruptedException {

		try {
			logger.info("Enter Customer Details For Regular Order");
			WaitUtility.pause(AfmCommonConstants.ONE);
			boolean isOrderButtonDisplayed = CommonActions
					.verifyElementDisplayed(AfmNewOrderConstants.PARENT_ORDER_BUTTON);
			CommonActions.clickOnElement(AfmNewOrderConstants.PARENT_ORDER_BUTTON);
			CommonActions.clickOnElement(AfmNewOrderConstants.NEW_ORDER_XPATH);
			CommonActions.clickOnElement(AfmNewOrderConstants.CUSTOMER_NAME_XPATH);
			WaitUtility.pause(AfmCommonConstants.THREE);
			sizeNumber = CommonActions.getListSizeOfWebElement(AfmCommonConstants.DROP_DOWN_OPTIONS_XPATH);
			String customerNameRandomNum = String.valueOf(CommonActions.generateIntegerRandomNumber(sizeNumber));

			customerName = CommonActions.getText(AfmNewOrderConstants.CUSTOMER_RANDOM_NAME_START_PATH
					.concat(customerNameRandomNum).concat(AfmNewOrderConstants.CUSTOMER_RANDOM_NAME_END_PATH));
			logger.info(customerName);

			CommonActions.clickOnElement(AfmNewOrderConstants.CUSTOMER_RANDOM_NAME_START_PATH
					.concat(customerNameRandomNum).concat(AfmNewOrderConstants.CUSTOMER_RANDOM_NAME_END_PATH));
			CommonActions.clickOnElement(AfmNewOrderConstants.SALESMAN_XPATH);
			sizeNumber = CommonActions.getListSizeOfWebElement(AfmCommonConstants.DROP_DOWN_OPTIONS_XPATH);
			String salesNameRandomNum = String.valueOf(CommonActions.generateIntegerRandomNumber(sizeNumber));

			salesName = CommonActions.getText(AfmNewOrderConstants.SALESMAN_RANDOM_NAME_START_PATH
					.concat(salesNameRandomNum).concat(AfmNewOrderConstants.SALSMAN_RANDOM_NAME_END_PATH));
			logger.info(salesName);

			CommonActions.clickOnElement(AfmNewOrderConstants.CUSTOMER_RANDOM_NAME_START_PATH.concat(salesNameRandomNum)
					.concat(AfmNewOrderConstants.CUSTOMER_RANDOM_NAME_END_PATH));
			CommonActions.clickAndEnterText(AfmNewOrderConstants.SALESMAN_COMMISSION_XPATH,
					AfmNewOrderConstants.SALESMAN_COMMISION);
			CommonActions.clickAndEnterText(AfmNewOrderConstants.ORDER_NOTES_XPATH,
					AfmNewOrderConstants.ORDER_NOTES_WRITE);
			captureScreen(driver, "enterCustomerDetails");
			return isOrderButtonDisplayed;
//			CommonActions.clickOnElement(AfmNewOrderConstants.ADD_CUSTOMER_ORDER);
//			return CommonActions.verifyElementDisplayed(AfmNewOrderConstants.SAVE_SHIPMENT_BUTTON);
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * Enter Shipment Details For Regular Order
	 */
	public boolean enterShipmentDetails() {

		try {
			logger.info("Enter Shipment Details For Regular Order");
			String dropFutureDate = CommonActions.getFutureDate(AfmCommonConstants.THREE).concat(" 12:00 AM");
			boolean isLocationButtonDisplayed = CommonActions
					.verifyElementDisplayed(AfmNewOrderConstants.SHIPMENT_PICKUP_LOCATION_XPATH);
			CommonActions.clickOnElement(AfmNewOrderConstants.SHIPMENT_PICKUP_LOCATION_XPATH);
			driver.manage().timeouts().implicitlyWait(1, TimeUnit.MINUTES);
			sizeNumber = CommonActions.getListSizeOfWebElement(AfmCommonConstants.DROP_DOWN_OPTIONS_XPATH);
			CommonActions.clickOnElement(AfmNewOrderConstants.SHIPMENT_RANDOM_PICUP_LOCATION_START_PATH
					.concat(String.valueOf(CommonActions.generateIntegerRandomNumber(sizeNumber)))
					.concat(AfmNewOrderConstants.SHIPMENT_RANDOM_PICUP_END_PATH));
			CommonActions.clickOnElement(AfmNewOrderConstants.SHIPMENT_DELIVERY_LOCATION_XPATH);
			driver.manage().timeouts().implicitlyWait(1, TimeUnit.MINUTES);
			sizeNumber = CommonActions.getListSizeOfWebElement(AfmCommonConstants.DROP_DOWN_OPTIONS_XPATH);
			CommonActions.clickOnElement(AfmNewOrderConstants.SHIPMENT_RANDOM_DROP_LOCATION_START_PATH
					.concat(String.valueOf(CommonActions.generateIntegerRandomNumber(sizeNumber)))
					.concat(AfmNewOrderConstants.SHIPMENT_RANDOM_DROP_LOCATION_END_PATH));
			CommonActions.clickAndClearText(AfmNewOrderConstants.DELIVER_DATE_XPATH);
			CommonActions.clickAndEnterText(AfmNewOrderConstants.DELIVER_DATE_XPATH, dropFutureDate);
			CommonActions.clickAndEnterText(AfmNewOrderConstants.SHIPMENT_REFRENCE_NUMBER_XPATH,
					AfmNewOrderConstants.SHIPMENT_REFERENCE_NUMBER_COMMENT);
			CommonActions.clickAndEnterText(AfmNewOrderConstants.SHIPMENT_NOTE_xpath,
					AfmNewOrderConstants.SHIPMENT_NOTE);
			CommonActions.clickOnElement(AfmNewOrderConstants.CSA_XPATH);
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			CommonActions.clickOnElement(AfmNewOrderConstants.HAZMAT_XPATH);
			captureScreen(driver, "enterShipmentDetails");
			return isLocationButtonDisplayed;
//			CommonActions.clickOnElement(AfmNewOrderConstants.ADD_SHIPMENT_ORDER);
//			return CommonActions.verifyElementDisplayed(AfmNewOrderConstants.UPDATECUSTOMER_REVENUEDETAILS);
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * Enter Revenue Details For Regular Order
	 */
	public boolean enterRevenueDetails() {

		try {
			logger.info("Enter Revenue Details For Regular Order");
			driver.manage().timeouts().implicitlyWait(2, TimeUnit.MINUTES);
			if (env.equals("https://afm2020.com/") || env.equals("http://192.168.1.22:2020/")
					|| env.equals("https://qa.afm2020.com/")) {
				CommonActions.selectByVisibleText(AfmCommonConstants.CURRENCY_XPATH,
						AfmCommonConstants.CURRENCY_VALUE_CAD);

			} else if (env.equals("http://india.afm2020.com/") || env.equals("http://192.168.1.20:2038")) {
				CommonActions.selectByVisibleText(AfmCommonConstants.CURRENCY_XPATH, AfmCommonConstants.CURRENCY_INR);
			}
			CommonActions.clickAndEnterText(AfmNewOrderConstants.REVENUE_FREIGHT_CHARGERATE_XPATH,
					AfmNewOrderConstants.REVENUE_FREIGHT_CHARGERATE_VALUE);
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			CommonActions.selectByVisibleText(AfmNewOrderConstants.REVENUE_FUEL_RATE_METHOD_XPATH,
					AfmNewOrderConstants.REVENUE_RATE_METHOD_FLAT_TEXT);
			CommonActions.clickAndEnterText(AfmNewOrderConstants.REVENUE_FUEL_RATE_XPATH,
					AfmNewOrderConstants.REVENUE_FUEL_RATE_value);
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			CommonActions.clickOnElement(AfmNewOrderConstants.UPDATECUSTOMER_REVENUEDETAILS);
			captureScreen(driver, "enterRevenueDetails");
			CommonActions.scrollPageDown();
			return CommonActions.verifyElementDisplayed(AfmNewOrderConstants.CREATE_TRIP_ORDER);
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * Trip Create for the Regular Order
	 */
	public boolean createTrip() {

		try {
			logger.info("Trip Create For The Regular Order");
			WaitUtility.pause(AfmCommonConstants.TWO);
			boolean isTripButtonDisplayed = CommonActions
					.verifyElementDisplayed(AfmNewOrderConstants.CREATE_TRIP_ORDER);
			CommonActions.clickOnElement(AfmNewOrderConstants.CREATE_TRIP_ORDER);
			captureScreen(driver, "createTrip");
			tripNumber = CommonActions.getText(AfmNewOrderConstants.GET_TRIP_NUMBER_XPATH).split(" ")[2];
			logger.info(tripNumber);
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			return isTripButtonDisplayed;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * Verify The Trip Create Successful Message
	 * 
	 * @return
	 */
	public String getTripText() {

		logger.info("Verify The Trip Create Successful Message");
		return driver.findElement(By.xpath(AfmNewOrderConstants.TRIP_VERIFY_XPATH)).getText();
	}

	/**
	 * Invoice create for the Regular Order
	 * 
	 * @throws IOException
	 */
	public boolean createInvoice() throws IOException {

		try {
			logger.info("Invoice Create For The Regular order");
			WaitUtility.pause(AfmCommonConstants.TWO);
			CommonActions.clickOnElement(AfmNewOrderConstants.TRIP_PLANNING_XPATH);
			orderNumber = CommonActions.getText(AfmNewOrderConstants.GET_ORDER_NUMBER_XPATH);
			logger.info(orderNumber);
			CommonActions.clickOnElement(AfmNewOrderConstants.INVOICE_ORDER_XPATH);
//			CommonActions.clickOnElement(AfmNewOrderConstants.INVOICE_CREATE_ORDER);
			boolean isSaveButtonDisplayed = CommonActions.verifyElementDisplayed(AfmNewOrderConstants.SAVE_INVOICE);
			CommonActions.clickOnElement(AfmNewOrderConstants.SAVE_INVOICE);
			if (CommonActions.getListSizeOfWebElement(AfmNewOrderConstants.CLOSE_CUSTOMER_EMAIL_SENDPOPUP) > 0) {
				CommonActions.clickOnElement(AfmNewOrderConstants.CLOSE_CUSTOMER_EMAIL_SENDPOPUP);
			}
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			captureScreen(driver, "createInvoice");
//			CommonActions.clickOnElement(AfmNewOrderConstants.CLOSE_INVOICE_POPUP);
			return isSaveButtonDisplayed;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * Fill Assets Details For Regular Order
	 * 
	 * @return
	 */

	public boolean fillAssertDetails() {

		try {
			logger.info("Fill The Asserts Details For Trip Add Item");
			boolean isTruckButtonDisplayed = CommonActions
					.verifyElementDisplayed(AfmNewOrderConstants.TRUCK_NUMBER_XPATH);
			driver.manage().timeouts().implicitlyWait(1, TimeUnit.MINUTES);
			CommonActions.clickOnElement(AfmNewOrderConstants.TRUCK_NUMBER_XPATH);
			sizeNumber = CommonActions.getListSizeOfWebElement(AfmCommonConstants.DROP_DOWN_OPTIONS_XPATH);
			truckName = String.valueOf(CommonActions.generateIntegerRandomNumber(sizeNumber));
			truckReferenceName = CommonActions.getText(AfmNewOrderConstants.TRUCK_RANDOM_PICUP_LOCATION_START_PATH
					.concat(truckName).concat(AfmNewOrderConstants.TRUCK_RANDOM_PICUP_END_PATH));
			logger.info(truckReferenceName);
			CommonActions.clickOnElement(AfmNewOrderConstants.TRUCK_RANDOM_PICUP_LOCATION_START_PATH.concat(truckName)
					.concat(AfmNewOrderConstants.TRUCK_RANDOM_PICUP_END_PATH));
			if (CommonActions.verifyElementDisplayed(AfmNewOrderConstants.CONFIRM_TRUCK_NUMBER_ONE) == true) {
				CommonActions.clickOnElement(AfmNewOrderConstants.CONFIRM_TRUCK_NUMBER_ONE);
				if (CommonActions.verifyElementDisplayed(AfmNewOrderConstants.CONFIRM_TRUCK_NUMBER_ONE) == true) {
					CommonActions.clickOnElement(AfmNewOrderConstants.CONFIRM_TRUCK_NUMBER_ONE);
				}
			}
			WaitUtility.pause(AfmCommonConstants.FIVE);
			CommonActions.clickOnElement(AfmNewOrderConstants.DRIVER_NAME_XPATH);
			driver.manage().timeouts().implicitlyWait(1, TimeUnit.MINUTES);
			sizeNumber = CommonActions.getListSizeOfWebElement(AfmCommonConstants.DROP_DOWN_OPTIONS_XPATH);
			driverName = String.valueOf(CommonActions.generateIntegerRandomNumber(sizeNumber));
			driverReferenceName = CommonActions.getText(AfmNewOrderConstants.DRIVER_NUMBER_START_PATH.concat(driverName)
					.concat(AfmNewOrderConstants.DRIVER_NUMBER_END_PATH)).split(" ")[0];
			logger.info(driverReferenceName);
			CommonActions.clickOnElement(AfmNewOrderConstants.DRIVER_NUMBER_START_PATH.concat(driverName)
					.concat(AfmNewOrderConstants.DRIVER_NUMBER_END_PATH));
			if (CommonActions.verifyElementDisplayed(AfmNewOrderConstants.DRIVER_NUMBER_CONFIRM) == true) {
				CommonActions.clickOnElement(AfmNewOrderConstants.DRIVER_NUMBER_CONFIRM);
				if (CommonActions.verifyElementDisplayed(AfmNewOrderConstants.DRIVER_NUMBER_CONFIRM) == true) {
					CommonActions.clickOnElement(AfmNewOrderConstants.DRIVER_NUMBER_CONFIRM);
				}
			}
			WaitUtility.pause(AfmCommonConstants.FIVE);
			CommonActions.clickOnElement(AfmNewOrderConstants.DRIVER_TWO_XPATH);
			driver.manage().timeouts().implicitlyWait(1, TimeUnit.MINUTES);
			sizeNumber = CommonActions.getListSizeOfWebElement(AfmCommonConstants.DROP_DOWN_OPTIONS_XPATH);
			driverTwoName = String.valueOf(CommonActions.generateIntegerRandomNumber(sizeNumber));
			driverTwoReferenceName = CommonActions.getText(AfmNewOrderConstants.DRIVER_TWO_START_PATH
					.concat(driverTwoName).concat(AfmNewOrderConstants.DRIVER_TWO_END_PATH)).split(" ")[0];
			logger.info(driverTwoReferenceName);
			CommonActions.clickOnElement(AfmNewOrderConstants.DRIVER_NUMBER_START_PATH.concat(driverTwoName)
					.concat(AfmNewOrderConstants.DRIVER_NUMBER_END_PATH));
			WaitUtility.pause(AfmCommonConstants.TWO);
			if (CommonActions.verifyElementDisplayed(AfmNewOrderConstants.DRIVER_NUMBER_CONFIRM) == true) {
				CommonActions.clickOnElement(AfmNewOrderConstants.DRIVER_NUMBER_CONFIRM);
				if (CommonActions.verifyElementDisplayed(AfmNewOrderConstants.DRIVER_NUMBER_CONFIRM) == true) {
					CommonActions.clickOnElement(AfmNewOrderConstants.DRIVER_NUMBER_CONFIRM);
				}
			}
			WaitUtility.pause(AfmCommonConstants.THREE);
			CommonActions.clickOnElement(AfmNewOrderConstants.TRAIER_NUMBER_XPATH);
			driver.manage().timeouts().implicitlyWait(1, TimeUnit.MINUTES);
			sizeNumber = CommonActions.getListSizeOfWebElement(AfmCommonConstants.DROP_DOWN_OPTIONS_XPATH);
			trailerName = String.valueOf(CommonActions.generateIntegerRandomNumber(sizeNumber));
			trailerReferenceName = CommonActions.getText(AfmNewOrderConstants.DRIVER_TWO_START_PATH.concat(trailerName)
					.concat(AfmNewOrderConstants.DRIVER_TWO_END_PATH));
			logger.info(trailerReferenceName);
			CommonActions.clickOnElement(AfmNewOrderConstants.DRIVER_NUMBER_START_PATH.concat(trailerName)
					.concat(AfmNewOrderConstants.DRIVER_NUMBER_END_PATH));
			WaitUtility.pause(AfmCommonConstants.TWO);
			if (CommonActions.verifyElementDisplayed(AfmNewOrderConstants.DRIVER_NUMBER_CONFIRM) == true) {
				CommonActions.clickOnElement(AfmNewOrderConstants.DRIVER_NUMBER_CONFIRM);
				if (CommonActions.verifyElementDisplayed(AfmNewOrderConstants.DRIVER_NUMBER_CONFIRM) == true) {
					CommonActions.clickOnElement(AfmNewOrderConstants.DRIVER_NUMBER_CONFIRM);
				}
			}
			WaitUtility.pause(AfmCommonConstants.FIVE);
			CommonActions.clickOnElement(AfmNewOrderConstants.ASSETS_DETAILS_SAVE_XPATH);
			captureScreen(driver, "fillAssertDetails");
			return isTruckButtonDisplayed;
		} catch (Exception e) {
			return false;
		}
	}

	public String verifyAssertDetails() {
		logger.info("Verfy The Asserts Details Message");
		return driver.findElement(By.xpath(AfmNewOrderConstants.DISPATCH_TRIP_XPATH)).getText();
	}

	/**
	 * Add Notes Regular Order
	 */
	public boolean addNotes() {

		try {
			logger.info("Add Note Trip");
			CommonActions.clickOnElement(AfmNewOrderConstants.ADD_NOTE_XPATH);
			CommonActions.clickAndEnterText(AfmNewOrderConstants.NOTE_XPATH, AfmNewOrderConstants.NOTE_VALUE);
			CommonActions.clickOnElement(AfmNewOrderConstants.SAVE_NOTE_XPATH);
			boolean isCloseNoteButtonDisplayed = CommonActions
					.verifyElementDisplayed(AfmNewOrderConstants.CLOSE_NOTE_XPATH);
			CommonActions.clickOnElement(AfmNewOrderConstants.CLOSE_NOTE_XPATH);
			captureScreen(driver, "addNotes");
			return isCloseNoteButtonDisplayed;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * Assets Fill Truck Name
	 * 
	 * @return
	 */
	public String truckName() {

		logger.info("Fill The Asserts Details For Trip Add Item");
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.MINUTES);
		CommonActions.clickOnElement(AfmNewOrderConstants.TRUCK_NUMBER_XPATH);
		sizeNumber = CommonActions.getListSizeOfWebElement(AfmCommonConstants.DROP_DOWN_OPTIONS_XPATH);
		truckName = String.valueOf(CommonActions.generateIntegerRandomNumber(sizeNumber));
		truckReferenceName = CommonActions.getText(AfmNewOrderConstants.TRUCK_RANDOM_PICUP_LOCATION_START_PATH
				.concat(truckName).concat(AfmNewOrderConstants.TRUCK_RANDOM_PICUP_END_PATH));
		logger.info(truckReferenceName);
		CommonActions.clickOnElement(AfmNewOrderConstants.TRUCK_RANDOM_PICUP_LOCATION_START_PATH.concat(truckName)
				.concat(AfmNewOrderConstants.TRUCK_RANDOM_PICUP_END_PATH));
		if (CommonActions.verifyElementDisplayed(AfmNewOrderConstants.CONFIRM_TRUCK_NUMBER_ONE) == true) {
			CommonActions.clickOnElement(AfmNewOrderConstants.CONFIRM_TRUCK_NUMBER_ONE);
			if (CommonActions.verifyElementDisplayed(AfmNewOrderConstants.CONFIRM_TRUCK_NUMBER_ONE) == true) {
				CommonActions.clickOnElement(AfmNewOrderConstants.CONFIRM_TRUCK_NUMBER_ONE);
			}
		}
		return truckReferenceName;
	}

	/**
	 * Assets Fill Driver Name One
	 * 
	 * @return
	 */
	public String driverNameOne() {

		WaitUtility.pause(AfmCommonConstants.FIVE);
		CommonActions.clickOnElement(AfmNewOrderConstants.DRIVER_NAME_XPATH);
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.MINUTES);
		sizeNumber = CommonActions.getListSizeOfWebElement(AfmCommonConstants.DROP_DOWN_OPTIONS_XPATH);
		driverName = String.valueOf(CommonActions.generateIntegerRandomNumber(sizeNumber));
		driverReferenceName = CommonActions.getText(AfmNewOrderConstants.DRIVER_NUMBER_START_PATH.concat(driverName)
				.concat(AfmNewOrderConstants.DRIVER_NUMBER_END_PATH)).split(" ")[0];
		logger.info(driverReferenceName);
		CommonActions.clickOnElement(AfmNewOrderConstants.DRIVER_NUMBER_START_PATH.concat(driverName)
				.concat(AfmNewOrderConstants.DRIVER_NUMBER_END_PATH));
		if (CommonActions.verifyElementDisplayed(AfmNewOrderConstants.DRIVER_NUMBER_CONFIRM) == true) {
			CommonActions.clickOnElement(AfmNewOrderConstants.DRIVER_NUMBER_CONFIRM);
			if (CommonActions.verifyElementDisplayed(AfmNewOrderConstants.DRIVER_NUMBER_CONFIRM) == true) {
				CommonActions.clickOnElement(AfmNewOrderConstants.DRIVER_NUMBER_CONFIRM);
			}
		}
		return driverReferenceName;
	}

	/**
	 * Asserts Fill Driver Name Two
	 * 
	 * @return
	 */
	public String driverNameTwo() {

		WaitUtility.pause(AfmCommonConstants.FIVE);
		CommonActions.clickOnElement(AfmNewOrderConstants.DRIVER_TWO_XPATH);
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.MINUTES);
		sizeNumber = CommonActions.getListSizeOfWebElement(AfmCommonConstants.DROP_DOWN_OPTIONS_XPATH);
		driverTwoName = String.valueOf(CommonActions.generateIntegerRandomNumber(sizeNumber));
		driverTwoReferenceName = CommonActions.getText(AfmNewOrderConstants.DRIVER_TWO_START_PATH.concat(driverTwoName)
				.concat(AfmNewOrderConstants.DRIVER_TWO_END_PATH)).split(" ")[0];
		logger.info(driverTwoReferenceName);
		CommonActions.clickOnElement(AfmNewOrderConstants.DRIVER_NUMBER_START_PATH.concat(driverTwoName)
				.concat(AfmNewOrderConstants.DRIVER_NUMBER_END_PATH));
		WaitUtility.pause(AfmCommonConstants.TWO);
		if (CommonActions.verifyElementDisplayed(AfmNewOrderConstants.DRIVER_NUMBER_CONFIRM) == true) {
			CommonActions.clickOnElement(AfmNewOrderConstants.DRIVER_NUMBER_CONFIRM);
			if (CommonActions.verifyElementDisplayed(AfmNewOrderConstants.DRIVER_NUMBER_CONFIRM) == true) {
				CommonActions.clickOnElement(AfmNewOrderConstants.DRIVER_NUMBER_CONFIRM);
			}
		}
		return driverTwoReferenceName;
	}

	/**
	 * Assets Fill Trailer Name
	 * 
	 * @return
	 * @throws IOException
	 */
	public String trailerName() throws IOException {

		WaitUtility.pause(AfmCommonConstants.THREE);
		CommonActions.clickOnElement(AfmNewOrderConstants.TRAIER_NUMBER_XPATH);
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.MINUTES);
		sizeNumber = CommonActions.getListSizeOfWebElement(AfmCommonConstants.DROP_DOWN_OPTIONS_XPATH);
		trailerName = String.valueOf(CommonActions.generateIntegerRandomNumber(sizeNumber));
		trailerReferenceName = CommonActions.getText(AfmNewOrderConstants.DRIVER_TWO_START_PATH.concat(trailerName)
				.concat(AfmNewOrderConstants.DRIVER_TWO_END_PATH));
		logger.info(trailerReferenceName);
		CommonActions.clickOnElement(AfmNewOrderConstants.DRIVER_NUMBER_START_PATH.concat(trailerName)
				.concat(AfmNewOrderConstants.DRIVER_NUMBER_END_PATH));
		WaitUtility.pause(AfmCommonConstants.TWO);
		if (CommonActions.verifyElementDisplayed(AfmNewOrderConstants.DRIVER_NUMBER_CONFIRM) == true) {
			CommonActions.clickOnElement(AfmNewOrderConstants.DRIVER_NUMBER_CONFIRM);
			if (CommonActions.verifyElementDisplayed(AfmNewOrderConstants.DRIVER_NUMBER_CONFIRM) == true) {
				CommonActions.clickOnElement(AfmNewOrderConstants.DRIVER_NUMBER_CONFIRM);
			}
		}
		CommonActions.clickAndEnterText(AfmNewOrderConstants.SEAL_NUMBER_XPATH, AfmNewOrderConstants.SEAL_NUMBER_VALUE);
		WaitUtility.pause(AfmCommonConstants.FIVE);
		CommonActions.clickOnElement(AfmNewOrderConstants.ASSETS_DETAILS_SAVE_XPATH);
		captureScreen(driver, "fillAssertDetails");
		return trailerReferenceName;
	}

	/**
	 * Trip Journey For Regular Order
	 */
	public boolean tripJourney() {

		try {
			logger.info("Filled Trip Journey For Regular Order Details");
			WaitUtility.pause(AfmCommonConstants.ONE);
			CommonActions.clickOnElement(AfmNewOrderConstants.DISPATCH_TRIP_XPATH);
			WaitUtility.pause(AfmCommonConstants.ONE);
			if (CommonActions
					.getListSizeOfWebElement(AfmTripOutSourceConstants.CONTINUE_XPATH) > AfmCommonConstants.ZERO) {
				CommonActions.clickOnElement(AfmTripOutSourceConstants.CONTINUE_XPATH);
				WaitUtility.pause(AfmCommonConstants.TWO);
			}
			if (CommonActions.getListSizeOfWebElement(
					AfmTripOutSourceConstants.DISPATCH_TRIP_OK_VERIFIER) > AfmCommonConstants.ZERO) {
				CommonActions.clickOnElement(AfmTripOutSourceConstants.DISPATCH_TRIP_OK_WARNING);
				WaitUtility.pause(AfmCommonConstants.TWO);

				if (CommonActions.getListSizeOfWebElement(
						AfmTripOutSourceConstants.SEND_MESSAGE_DRIVER_XPATH) > AfmCommonConstants.ZERO) {
					CommonActions.clickOnElement(AfmTripOutSourceConstants.SEND_MESSAGE_DRIVER_XPATH);
					WaitUtility.pause(AfmCommonConstants.TWO);
				}
			} else if (CommonActions.getListSizeOfWebElement(
					AfmTripOutSourceConstants.SEND_MESSAGE_DRIVER_XPATH) > AfmCommonConstants.ZERO) {
				CommonActions.clickOnElement(AfmTripOutSourceConstants.SEND_MESSAGE_DRIVER_XPATH);
				WaitUtility.pause(AfmCommonConstants.THREE);
			}
			if (CommonActions.getListSizeOfWebElement(
					AfmTripOutSourceConstants.START_TRIP_VERIFIER_XPATH) == AfmCommonConstants.ZERO) {
				CommonActions.clickOnElement(AfmTripOutSourceConstants.START_TRIP_XPATH);
			}

			WaitUtility.pause(AfmCommonConstants.TWO);
			CommonActions.clickOnElement(AfmNewOrderConstants.QUICK_DELIVER_XPATH);
			WaitUtility.pause(AfmCommonConstants.ONE);
			boolean isTripButtonDisplayed = CommonActions
					.verifyElementDisplayed(AfmNewOrderConstants.TRIP_ASSERTS_STATUS);
			CommonActions.clickOnElement(AfmNewOrderConstants.TRIP_ASSERTS_STATUS);
			captureScreen(driver, "tripJourney");
			return isTripButtonDisplayed;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * Settlement For The Regular Order(Owner And Driver One)
	 * 
	 * @throws InterruptedException
	 */
	public boolean settledmentOwner() throws InterruptedException {

		try {
			logger.info("Filled The Owner Settlement Details");
			WaitUtility.pause(AfmCommonConstants.TWO);
			CommonActions.clickOnElement(AfmNewOrderConstants.SETTLED_XPATH);
			WaitUtility.pause(AfmCommonConstants.TWO);
			CommonActions.selectByVisibleText(AfmNewOrderConstants.OWNER_PAYROLL_TYPE_XAPTH,
					AfmNewOrderConstants.OWNER_FREIGHT_METHOD_FLAT_TEXT);
			CommonActions.clickAndClearText(AfmNewOrderConstants.OWNER_FREIGHT_AMOUNT_XPATH);
			CommonActions.clickAndEnterText(AfmNewOrderConstants.OWNER_FREIGHT_AMOUNT_XPATH,
					AfmNewOrderConstants.OWNER_FREIGHT_AMOUNT_VALUE);
			CommonActions.clickOnElement(AfmNewOrderConstants.ADDITION_DEDUCTION_XPATH);
			CommonActions.selectByVisibleText(AfmNewOrderConstants.PAYMENT_TYPE_XPATH,
					AfmNewOrderConstants.PAYMENT_TYPE_VALUE);
			CommonActions.selectByVisibleText(AfmNewOrderConstants.PAYMENT_MODE_XPATH,
					AfmNewOrderConstants.PAYMENT_MODE_VALUE);
			CommonActions.clickAndClearText(AfmNewOrderConstants.PAYMENT_AMOUNT_XPATH);
			CommonActions.clickAndEnterText(AfmNewOrderConstants.PAYMENT_AMOUNT_XPATH,
					AfmNewOrderConstants.PAYMENT_AMOUNT_VALUE);
			CommonActions.clickOnElement(AfmNewOrderConstants.ADD_XPATH);
			CommonActions.scrollPageDown();
			driver.manage().timeouts().implicitlyWait(1, TimeUnit.MINUTES);
			CommonActions.clickOnElement(AfmNewOrderConstants.PAYMENT_GET_XPATH);
			CommonActions.clickOnElement(AfmNewOrderConstants.FINAL_OWNER_OPERATOR);
			WaitUtility.pause(AfmCommonConstants.TWO);
			boolean isFinalConfirmationButtondisplayed = CommonActions
					.verifyElementDisplayed(AfmNewOrderConstants.FINAL_CONFORMATION_OWNER);
			CommonActions.clickOnElement(AfmNewOrderConstants.FINAL_CONFORMATION_OWNER);
			WaitUtility.pause(AfmCommonConstants.FIVE);
			CommonActions.clickOnElement(AfmNewOrderConstants.EMAIL_SETTLEMENT_MESSAGE_POPUP);
			captureScreen(driver, "settledmentOwner");
			return isFinalConfirmationButtondisplayed;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * Settlement For the Driver One
	 */
	public boolean settlementDriverOne() {

		try {
			logger.info("Settlement Driver One");
			WaitUtility.pause(AfmCommonConstants.THREE);
			CommonActions.clickOnElement(AfmNewOrderConstants.CONTINUE_DRIVER_SETTLEMENT);
			CommonActions.clickOnElement(AfmNewOrderConstants.DRIVER_ONE_DEDUCATION_XPATH);
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			boolean isDriverButtonDisplayed = CommonActions
					.verifyElementDisplayed(AfmNewOrderConstants.DRIVER_DEDUCATION_OWNER);
			CommonActions.clickOnElement(AfmNewOrderConstants.DRIVER_DEDUCATION_OWNER);
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			CommonActions.selectByVisibleText(AfmNewOrderConstants.OWNER_PAYROLL_TYPE_XAPTH,
					AfmNewOrderConstants.PAYROLL_TYPE_VALUE);
			CommonActions.clickAndClearText(AfmNewOrderConstants.DELIVERY_DISTANCE_XPATH);
			CommonActions.clickAndEnterText(AfmNewOrderConstants.DELIVERY_DISTANCE_XPATH,
					AfmNewOrderConstants.DELIVERY_DISTANCE_VALUE);
			CommonActions.clickAndClearText(AfmNewOrderConstants.LOADED_DISTANCE_RATE);
			CommonActions.clickAndEnterText(AfmNewOrderConstants.LOADED_DISTANCE_RATE,
					AfmNewOrderConstants.LOADED_DISTANCE_RATE_VALUE);
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			CommonActions.clickOnElement(AfmNewOrderConstants.DRIVER_GET_SETTLEMENT);
			CommonActions.clickOnElement(AfmNewOrderConstants.DRIVER_FINAL_SETTLEMENT);
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			CommonActions.clickOnElement(AfmNewOrderConstants.FINAL_CONFORMATION_DRIVER);
			WaitUtility.pause(AfmCommonConstants.THREE);
			CommonActions.clickOnElement(AfmNewOrderConstants.DRIVER_SETTLEMENT_EMAIL_POPUP);
			captureScreen(driver, "settlementDriverOne");
			WaitUtility.pause(AfmCommonConstants.ONE);
			return isDriverButtonDisplayed;
//			boolean isDriverSettlementButtonDisplayed = CommonActions
//					.verifyElementDisplayed(AfmNewOrderConstants.DRIVER_SETTLEMENT_CLOSE);
//			CommonActions.clickOnElement(AfmNewOrderConstants.DRIVER_SETTLEMENT_CLOSE);
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * Settlement Driver Two
	 */
	public boolean settlementDriverTwo() {

		try {
			logger.info("Filled The Driver Two Settlement Details");
			CommonActions.clickOnElement(AfmNewOrderConstants.DRIVER_SETTLEMENT_CLOSE);
			boolean isSettledButtonDisplayed = CommonActions.verifyElementDisplayed(AfmNewOrderConstants.SETTLED_XPATH);
			CommonActions.clickOnElement(AfmNewOrderConstants.SETTLED_XPATH);
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			CommonActions.clickOnElement(AfmNewOrderConstants.OWNER_SETTLEMENT_OPEN);
			CommonActions.clickOnElement(AfmNewOrderConstants.DRIVER_TWO_DEDUCATION_XPATH);
			CommonActions.clickOnElement(AfmNewOrderConstants.DRIVER_TWO_DEDUCATION_OWNER);
			CommonActions.selectByVisibleText(AfmNewOrderConstants.OWNER_PAYROLL_TYPE_XAPTH,
					AfmNewOrderConstants.PAYROLL_TYPE_PERCENTAGE);
			CommonActions.clickOnElement(AfmNewOrderConstants.PAYROLL_TYPE_XPATH);
			CommonActions.clickAndEnterText(AfmNewOrderConstants.PAYROLL_TYPE_XPATH,
					AfmNewOrderConstants.PAYROLL_TYPE_VALUEE);
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			CommonActions.clickOnElement(AfmNewOrderConstants.DRIVER_GET_SETTLEMENT);
			CommonActions.clickOnElement(AfmNewOrderConstants.DRIVER_FINAL_SETTLEMENT);
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			CommonActions.clickOnElement(AfmNewOrderConstants.FINAL_CONFORMATION_DRIVER);
			WaitUtility.pause(AfmCommonConstants.THREE);
			CommonActions.clickOnElement(AfmNewOrderConstants.DRIVER_TWO_SETTLEMENT_EMAIL_POPUP);
			WaitUtility.pause(AfmCommonConstants.TWO);
			return isSettledButtonDisplayed;
//			boolean isDriverSettlementButtonDisplayed = CommonActions
//					.verifyElementDisplayed(AfmNewOrderConstants.DRIVER_TWO_SETTLEDMENT_CLOSE);
//			CommonActions.clickOnElement(AfmNewOrderConstants.DRIVER_TWO_SETTLEDMENT_CLOSE);
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * Filled Payment Truck Owner For The Regular Order
	 * 
	 * @throws IOException
	 */
	public boolean paymentTruckOwner() throws IOException {

		try {
			logger.info(" Filled Payment Truck Owner For The Regular Order");
			CommonActions.clickOnElement(AfmNewOrderConstants.PAYMENT_XPATH);
			CommonActions.clickOnElement(AfmNewOrderConstants.CREATE_PAYMENT_OWNER_XPATH);
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			CommonActions.clickOnElement(AfmNewOrderConstants.ACCOUNT_NAME_XPATH);
			sizeNumber = CommonActions.getListSizeOfWebElement(AfmCommonConstants.DROP_DOWN_OPTIONS_XPATH);
			truckOwnerPayment = String.valueOf(CommonActions.generateIntegerRandomNumber(sizeNumber));
			truckOwnerReference = CommonActions.getText(AfmNewOrderConstants.ACCOUNT_START_PATH
					.concat(truckOwnerPayment).concat(AfmNewOrderConstants.ACCOUNT_END_PATH)).split(" ")[0];
			logger.info(truckOwnerReference);
			CommonActions.clickOnElement(AfmNewOrderConstants.ACCOUNT_START_PATH.concat(truckOwnerPayment)
					.concat(AfmNewOrderConstants.ACCOUNT_END_PATH));
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			CommonActions.clickOnElement(AfmNewOrderConstants.PAYMENT_METHOD_XPATH);
			sizeNumber = CommonActions.getListSizeOfWebElement(AfmCommonConstants.PAYMENT_ACCOUNT_XPATH);
			CommonActions.clickOnElement(AfmNewOrderConstants.PAYMENT_START_PATH
					.concat(String.valueOf(CommonActions.generateIntegerRandomNumber(sizeNumber)))
					.concat(AfmNewOrderConstants.PAYMENT_END_PATH));
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//			CommonActions.clickOnElement(AfmNewOrderConstants.PAYMENT_DRAFT_XPATH);
			boolean isDriverSettlementButtonDisplayed = CommonActions
					.verifyElementDisplayed(AfmNewOrderConstants.PAYMENT_SUBMIT_XPATH);
			CommonActions.clickOnElement(AfmNewOrderConstants.PAYMENT_SUBMIT_XPATH);
			WaitUtility.pause(AfmCommonConstants.FIVE);
			CommonActions.clickOnElement(AfmNewOrderConstants.EMAIL_PAYMENT_XPATH);
			captureScreen(driver, "paymentTruckOwner");
			return isDriverSettlementButtonDisplayed;
		} catch (Exception e) {
			captureScreen(driver, "paymentTruckOwner");
			return false;
		}
	}

	/**
	 * Filled Payment Driver One For The Regular order
	 * 
	 * @throws IOException
	 */
	public boolean paymentDriverOne() throws IOException {

		try {
			logger.info("Filled Payment Driver One For The Regular Order");
			boolean isDriverSettlementButtonDisplayed = CommonActions
					.verifyElementDisplayed(AfmNewOrderConstants.CREATE_PAYMENT_DRIVER_ONE_XPATH);
			CommonActions.clickOnElement(AfmNewOrderConstants.CREATE_PAYMENT_DRIVER_ONE_XPATH);
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			CommonActions.clickOnElement(AfmNewOrderConstants.ACCOUNT_NAME_XPATH);
			sizeNumber = CommonActions.getListSizeOfWebElement(AfmCommonConstants.PAYMENT_ACCOUNT_XPATH);
			CommonActions.clickOnElement(AfmNewOrderConstants.ACCOUNT_START_PATH
					.concat(String.valueOf(CommonActions.generateIntegerRandomNumber(sizeNumber)))
					.concat(AfmNewOrderConstants.ACCOUNT_END_PATH));
			CommonActions.clickOnElement(AfmNewOrderConstants.PAYMENT_METHOD_XPATH);
			WaitUtility.pause(AfmCommonConstants.ONE);
			sizeNumber = CommonActions.getListSizeOfWebElement(AfmCommonConstants.PAYMENT_ACCOUNT_XPATH);
			CommonActions.clickOnElement(AfmNewOrderConstants.PAYMENT_START_PATH
					.concat(String.valueOf(CommonActions.generateIntegerRandomNumber(sizeNumber)))
					.concat(AfmNewOrderConstants.PAYMENT_END_PATH));
//			CommonActions.clickOnElement(AfmNewOrderConstants.PAYMENT_DRAFT_XPATH);
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			CommonActions.clickOnElement(AfmNewOrderConstants.PAYMENT_SUBMIT_XPATH);
			WaitUtility.pause(AfmCommonConstants.FIVE);
			CommonActions.clickOnElement(AfmNewOrderConstants.EMAIL_PAYMENT_XPATH);
			captureScreen(driver, "paymentDriverOne");
			return isDriverSettlementButtonDisplayed;
		} catch (Exception e) {
			captureScreen(driver, "paymentDriverOneAndDriverTwo");
			return false;
		}
	}

	/**
	 * Filled Payment Driver One
	 */
	public boolean paymentDriverTwo() {

		try {
			logger.info("Filled the Driver Two Payment");
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			CommonActions.clickOnElement(AfmNewOrderConstants.CREATE_PAYMENT_DRIVER_TWO_XPATH);
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			CommonActions.scrollPageUsingX_Axis(AfmCommonConstants.X_AXIS_TWO_THOUSAND, AfmCommonConstants.X_AXIS_ZERO);
			WaitUtility.pause(AfmCommonConstants.TWO);
			CommonActions.clickAndClearText(AfmNewOrderConstants.PARCIALLY_PAID_DRIVER_XPATH);
			CommonActions.clickAndEnterText(AfmNewOrderConstants.PARCIALLY_PAID_DRIVER_XPATH,
					AfmNewOrderConstants.PARCIALLY_PAID_VALUE);
			WaitUtility.pause(AfmCommonConstants.ONE);
			CommonActions.clickOnElement(AfmNewOrderConstants.ACCOUNT_NAME_XPATH);
			WaitUtility.pause(AfmCommonConstants.ONE);
			sizeNumber = CommonActions.getListSizeOfWebElement(AfmCommonConstants.PAYMENT_ACCOUNT_XPATH);
			CommonActions.clickOnElement(AfmNewOrderConstants.ACCOUNT_START_PATH
					.concat(String.valueOf(CommonActions.generateIntegerRandomNumber(sizeNumber)))
					.concat(AfmNewOrderConstants.ACCOUNT_END_PATH));
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			CommonActions.clickOnElement(AfmNewOrderConstants.PAYMENT_METHOD_XPATH);
			WaitUtility.pause(AfmCommonConstants.ONE);
			sizeNumber = CommonActions.getListSizeOfWebElement(AfmCommonConstants.PAYMENT_ACCOUNT_XPATH);
			CommonActions.clickOnElement(AfmNewOrderConstants.PAYMENT_START_PATH
					.concat(String.valueOf(CommonActions.generateIntegerRandomNumber(sizeNumber)))
					.concat(AfmNewOrderConstants.PAYMENT_END_PATH));
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//			CommonActions.clickOnElement(AfmNewOrderConstants.PAYMENT_DRAFT_XPATH);
			driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
			CommonActions.clickOnElement(AfmNewOrderConstants.PAYMENT_SUBMIT_XPATH);
			WaitUtility.pause(AfmCommonConstants.THREE);
			CommonActions.clickOnElement(AfmNewOrderConstants.EMAIL_PAYMENT_XPATH);
			WaitUtility.pause(AfmCommonConstants.TWO);
			CommonActions.clickOnElement(AfmNewOrderConstants.CLOSE_PAYMENT_POPUP);
			boolean isSettledButtonDisplayed = CommonActions.verifyElementDisplayed(AfmNewOrderConstants.SETTLED_XPATH);
			CommonActions.clickOnElement(AfmNewOrderConstants.SETTLED_XPATH);
			captureScreen(driver, "paymentDriverTwo");
			driver.navigate().refresh();
			return isSettledButtonDisplayed;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * Create Receipt For The Regular Order
	 */
	public boolean createReceiptRegularOrder() {

		try {
			logger.info("Create Recipt For The Regular Order");
			CommonActions.clickOnElement(AfmNewOrderConstants.PARENT_ORDER_BUTTON);
			boolean isOrdersButtonDisplayed = CommonActions.verifyElementDisplayed(AfmNewOrderConstants.ORDERS_XPATH);
			CommonActions.clickOnElement(AfmNewOrderConstants.ORDERS_XPATH);
			WaitUtility.pause(AfmCommonConstants.THREE);
			CommonActions.clickOnElement(AfmNewOrderConstants.ACTION_BUTTON_XPATH_START_PATH.concat(orderNumber)
					.concat(AfmNewOrderConstants.ACTION_BUTTON_XPATH_END_PATH));
			WaitUtility.pause(AfmCommonConstants.TWO);
			CommonActions.clickOnElement(AfmNewOrderConstants.CREATE_RECEIPT_XPATH_START.concat(orderNumber)
					.concat(AfmNewOrderConstants.CREATE_RECEIPT_XPATH_END));
			CommonActions.clickOnElement(AfmNewOrderConstants.RECEIPT_PAYMENT_METHOD_XPATH);
			sizeNumber = CommonActions.getListSizeOfWebElement(AfmCommonConstants.PAYMENT_ACCOUNT_XPATH);
			CommonActions.clickOnElement(AfmNewOrderConstants.PAYMENT_START_PATH
					.concat(String.valueOf(CommonActions.generateIntegerRandomNumber(sizeNumber)))
					.concat(AfmNewOrderConstants.PAYMENT_END_PATH));
			WaitUtility.pause(AfmCommonConstants.ONE);
			CommonActions.clickAndEnterText(AfmNewOrderConstants.CASH_NUMBER_XATH,
					AfmNewOrderConstants.CASH_NUMBER_VALUE);
			WaitUtility.pause(AfmCommonConstants.TWO);
			CommonActions.clickOnElement(AfmCommonConstants.CREATE_NEW_ACCOUNT_XPATH);
			sizeNumber = CommonActions.getListSizeOfWebElement(AfmCommonConstants.DROP_DOWN_OPTIONS_XPATH);
			randomNumber = CommonActions.generateIntegerRandomNumber(sizeNumber);
			while (randomNumber == 1) {
				randomNumber = CommonActions.generateIntegerRandomNumber(sizeNumber);
			}
			CommonActions.clickOnElement(AfmNewOrderConstants.RECEIPT_START_PATH.concat(String.valueOf(randomNumber))
					.concat(AfmNewOrderConstants.RECEIPT_END_PATH));
			WaitUtility.pause(AfmCommonConstants.TWO);
			CommonActions.clickOnElement(AfmAccountingConstants.RECEIPT_DISCOUNT_TYPE_XPATH);
			sizeNumber = CommonActions.getListSizeOfWebElement(AfmCommonConstants.DROP_DOWN_OPTIONS_XPATH);
//			randomNumber = CommonActions.generateIntegerRandomNumber(sizeNumber);
//			while (randomNumber == 1) {
//				randomNumber = CommonActions.generateIntegerRandomNumber(sizeNumber);
//			}
//			CommonActions.clickOnElement(AfmNewOrderConstants.RECEIPT_START_PATH.concat(String.valueOf(randomNumber))
//					.concat(AfmNewOrderConstants.RECEIPT_END_PATH));
			String customerNameRandom = String.valueOf(CommonActions.generateIntegerRandomNumber(sizeNumber));

			receiptDiscountTypeName = CommonActions.getText(AfmNewOrderConstants.CUSTOMER_RANDOM_NAME_START_PATH
					.concat(customerNameRandom).concat(AfmNewOrderConstants.CUSTOMER_RANDOM_NAME_END_PATH));
			logger.info(receiptDiscountTypeName);
			CommonActions.clickOnElement(AfmNewOrderConstants.CUSTOMER_RANDOM_NAME_START_PATH.concat(customerNameRandom)
					.concat(AfmNewOrderConstants.CUSTOMER_RANDOM_NAME_END_PATH));

			CommonActions.clickOnElement(AfmNewOrderConstants.PAYMENT_ADDITION_XPATH);
			CommonActions.clickOnElement(AfmNewOrderConstants.ADDITION_PAYMENT_TYPE_XPATH);
			WaitUtility.pause(AfmCommonConstants.THREE);
			sizeNumber = CommonActions.getListSizeOfWebElement(AfmCommonConstants.DROP_DOWN_OPTIONS_XPATH);
			String customerNameRandomNum = String.valueOf(CommonActions.generateIntegerRandomNumber(sizeNumber));

			discountTypeName = CommonActions.getText(AfmNewOrderConstants.CUSTOMER_RANDOM_NAME_START_PATH
					.concat(customerNameRandomNum).concat(AfmNewOrderConstants.CUSTOMER_RANDOM_NAME_END_PATH));
			logger.info(discountTypeName);
			CommonActions.clickOnElement(AfmNewOrderConstants.CUSTOMER_RANDOM_NAME_START_PATH
					.concat(customerNameRandomNum).concat(AfmNewOrderConstants.CUSTOMER_RANDOM_NAME_END_PATH));
			CommonActions.clickAndEnterText(AfmNewOrderConstants.PAYMENT_MODE_XPATH,
					AfmNewOrderConstants.PAYMENT_MODE_VALUE);
			CommonActions.clickAndClearText(AfmNewOrderConstants.PAID_AMOUNT_XPATH);
			CommonActions.clickAndEnterText(AfmNewOrderConstants.PAID_AMOUNT_XPATH, AfmNewOrderConstants.PO_VALUE);
			WaitUtility.pause(AfmCommonConstants.ONE);
			CommonActions.clickOnElement(AfmNewOrderConstants.PAYMENT_ADD_XPATH);
			CommonActions.clickOnElement(AfmNewOrderConstants.AMOUNT_GET_XPATH);
			CommonActions.clickOnElement(AfmNewOrderConstants.RECEIPT_SAVE_XPATH);
			captureScreen(driver, "createReceiptRegularOrder");
			WaitUtility.pause(AfmCommonConstants.THREE);

			if (CommonActions.getListSizeOfWebElement(AfmNewOrderConstants.CLOSE_EMAIL_POPUP_XPATH) > 0) {
				CommonActions.clickOnElement(AfmNewOrderConstants.CLOSE_EMAIL_POPUP_XPATH);
			}
			driver.navigate().refresh();
			return isOrdersButtonDisplayed;
		} catch (Exception e) {
			return false;
		}
	}
}
