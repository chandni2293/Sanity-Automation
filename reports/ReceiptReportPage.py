# receipt_report_page.py

import os
import time
import random
from datetime import datetime, timedelta

from selenium.webdriver.common.by import By
from selenium.webdriver.support.ui import WebDriverWait, Select
from selenium.webdriver.support import expected_conditions as EC

from selenium import webdriver
from receipt_report_page import ReceiptReportPage

driver = webdriver.Chrome()
driver.get("https://afm2020.com/")

page = ReceiptReportPage(driver)

assert page.enter_customer_details()
assert page.enter_shipment_details()
assert page.enter_revenue_details()
assert page.create_trip()

class ReceiptReportPage:
    def __init__(self, driver, base_url="https://afm2020.com/"):
        self.driver = driver
        self.wait = WebDriverWait(driver, 20)
        self.base_url = base_url

        self.order_number = ""
        self.trip_number = ""
        self.driver_name = ""
        self.truck_owner_payment = ""
        self.name = ""
        self.carrier_name = ""
        self.customer_name = ""
        self.sales_name = ""
        self.truck_name = ""
        self.driver_two_name = ""
        self.trailer_name_value = ""
        self.trailer_reference_name = ""
        self.driver_two_reference_name = ""
        self.payment_reference_number = ""
        self.truck_reference_name = ""
        self.driver_reference_name = ""
        self.truck_owner_reference = ""
        self.vendor_name = ""
        self.vendor_name_random_num = ""
        self.receipt_number = ""
        self.discount_type_name = ""
        self.receipt_discount_type_name = ""

        os.makedirs("reports", exist_ok=True)

        # ---------------- Locators: replace with real XPaths ----------------
        self.PARENT_ORDER_BUTTON = "your_xpath"
        self.NEW_ORDER_XPATH = "your_xpath"
        self.CUSTOMER_NAME_XPATH = "your_xpath"
        self.DROP_DOWN_OPTIONS_XPATH = "your_xpath"
        self.CUSTOMER_RANDOM_NAME_START_PATH = "your_xpath_start"
        self.CUSTOMER_RANDOM_NAME_END_PATH = "your_xpath_end"
        self.SALESMAN_XPATH = "your_xpath"
        self.SALESMAN_RANDOM_NAME_START_PATH = "your_xpath_start"
        self.SALSMAN_RANDOM_NAME_END_PATH = "your_xpath_end"
        self.SALESMAN_COMMISSION_XPATH = "your_xpath"
        self.ORDER_NOTES_XPATH = "your_xpath"

        self.SHIPMENT_PICKUP_LOCATION_XPATH = "your_xpath"
        self.SHIPMENT_RANDOM_PICUP_LOCATION_START_PATH = "your_xpath_start"
        self.SHIPMENT_RANDOM_PICUP_END_PATH = "your_xpath_end"
        self.SHIPMENT_DELIVERY_LOCATION_XPATH = "your_xpath"
        self.SHIPMENT_RANDOM_DROP_LOCATION_START_PATH = "your_xpath_start"
        self.SHIPMENT_RANDOM_DROP_LOCATION_END_PATH = "your_xpath_end"
        self.DELIVER_DATE_XPATH = "your_xpath"
        self.SHIPMENT_REFRENCE_NUMBER_XPATH = "your_xpath"
        self.SHIPMENT_NOTE_XPATH = "your_xpath"
        self.CSA_XPATH = "your_xpath"
        self.HAZMAT_XPATH = "your_xpath"

        self.CURRENCY_XPATH = "your_xpath"
        self.REVENUE_FREIGHT_CHARGERATE_XPATH = "your_xpath"
        self.REVENUE_FUEL_RATE_METHOD_XPATH = "your_xpath"
        self.REVENUE_FUEL_RATE_XPATH = "your_xpath"
        self.UPDATECUSTOMER_REVENUEDETAILS = "your_xpath"
        self.CREATE_TRIP_ORDER = "your_xpath"
        self.GET_TRIP_NUMBER_XPATH = "your_xpath"
        self.TRIP_VERIFY_XPATH = "your_xpath"

        self.TRIP_PLANNING_XPATH = "your_xpath"
        self.GET_ORDER_NUMBER_XPATH = "your_xpath"
        self.INVOICE_ORDER_XPATH = "your_xpath"
        self.SAVE_INVOICE = "your_xpath"
        self.CLOSE_CUSTOMER_EMAIL_SENDPOPUP = "your_xpath"

        self.TRUCK_NUMBER_XPATH = "your_xpath"
        self.TRUCK_RANDOM_PICUP_LOCATION_START_PATH = "your_xpath_start"
        self.TRUCK_RANDOM_PICUP_END_PATH = "your_xpath_end"
        self.CONFIRM_TRUCK_NUMBER_ONE = "your_xpath"

        self.DRIVER_NAME_XPATH = "your_xpath"
        self.DRIVER_NUMBER_START_PATH = "your_xpath_start"
        self.DRIVER_NUMBER_END_PATH = "your_xpath_end"
        self.DRIVER_NUMBER_CONFIRM = "your_xpath"

        self.DRIVER_TWO_XPATH = "your_xpath"
        self.DRIVER_TWO_START_PATH = "your_xpath_start"
        self.DRIVER_TWO_END_PATH = "your_xpath_end"

        self.TRAIER_NUMBER_XPATH = "your_xpath"
        self.SEAL_NUMBER_XPATH = "your_xpath"
        self.ASSETS_DETAILS_SAVE_XPATH = "your_xpath"
        self.DISPATCH_TRIP_XPATH = "your_xpath"

        self.ADD_NOTE_XPATH = "your_xpath"
        self.NOTE_XPATH = "your_xpath"
        self.SAVE_NOTE_XPATH = "your_xpath"
        self.CLOSE_NOTE_XPATH = "your_xpath"

        self.CONTINUE_XPATH = "your_xpath"
        self.DISPATCH_TRIP_OK_VERIFIER = "your_xpath"
        self.DISPATCH_TRIP_OK_WARNING = "your_xpath"
        self.SEND_MESSAGE_DRIVER_XPATH = "your_xpath"
        self.START_TRIP_VERIFIER_XPATH = "your_xpath"
        self.START_TRIP_XPATH = "your_xpath"
        self.QUICK_DELIVER_XPATH = "your_xpath"
        self.TRIP_ASSERTS_STATUS = "your_xpath"

        self.SETTLED_XPATH = "your_xpath"
        self.OWNER_PAYROLL_TYPE_XAPTH = "your_xpath"
        self.OWNER_FREIGHT_AMOUNT_XPATH = "your_xpath"
        self.ADDITION_DEDUCTION_XPATH = "your_xpath"
        self.PAYMENT_TYPE_XPATH = "your_xpath"
        self.PAYMENT_MODE_XPATH = "your_xpath"
        self.PAYMENT_AMOUNT_XPATH = "your_xpath"
        self.ADD_XPATH = "your_xpath"
        self.PAYMENT_GET_XPATH = "your_xpath"
        self.FINAL_OWNER_OPERATOR = "your_xpath"
        self.FINAL_CONFORMATION_OWNER = "your_xpath"
        self.EMAIL_SETTLEMENT_MESSAGE_POPUP = "your_xpath"

        self.CONTINUE_DRIVER_SETTLEMENT = "your_xpath"
        self.DRIVER_ONE_DEDUCATION_XPATH = "your_xpath"
        self.DRIVER_DEDUCATION_OWNER = "your_xpath"
        self.DELIVERY_DISTANCE_XPATH = "your_xpath"
        self.LOADED_DISTANCE_RATE = "your_xpath"
        self.DRIVER_GET_SETTLEMENT = "your_xpath"
        self.DRIVER_FINAL_SETTLEMENT = "your_xpath"
        self.FINAL_CONFORMATION_DRIVER = "your_xpath"
        self.DRIVER_SETTLEMENT_EMAIL_POPUP = "your_xpath"
        self.DRIVER_SETTLEMENT_CLOSE = "your_xpath"

        self.OWNER_SETTLEMENT_OPEN = "your_xpath"
        self.DRIVER_TWO_DEDUCATION_XPATH = "your_xpath"
        self.DRIVER_TWO_DEDUCATION_OWNER = "your_xpath"
        self.PAYROLL_TYPE_XPATH = "your_xpath"
        self.DRIVER_TWO_SETTLEMENT_EMAIL_POPUP = "your_xpath"

        self.PAYMENT_XPATH = "your_xpath"
        self.CREATE_PAYMENT_OWNER_XPATH = "your_xpath"
        self.ACCOUNT_NAME_XPATH = "your_xpath"
        self.ACCOUNT_START_PATH = "your_xpath_start"
        self.ACCOUNT_END_PATH = "your_xpath_end"
        self.PAYMENT_METHOD_XPATH = "your_xpath"
        self.PAYMENT_ACCOUNT_XPATH = "your_xpath"
        self.PAYMENT_START_PATH = "your_xpath_start"
        self.PAYMENT_END_PATH = "your_xpath_end"
        self.PAYMENT_SUBMIT_XPATH = "your_xpath"
        self.EMAIL_PAYMENT_XPATH = "your_xpath"
        self.CREATE_PAYMENT_DRIVER_ONE_XPATH = "your_xpath"

        # ---------------- Test values: replace if needed ----------------
        self.SALESMAN_COMMISION = "10"
        self.ORDER_NOTES_WRITE = "Automation order notes"
        self.SHIPMENT_REFERENCE_NUMBER_COMMENT = "REF123"
        self.SHIPMENT_NOTE = "Automation shipment note"
        self.REVENUE_FREIGHT_CHARGERATE_VALUE = "1000"
        self.REVENUE_RATE_METHOD_FLAT_TEXT = "Flat"
        self.REVENUE_FUEL_RATE_VALUE = "100"
        self.CURRENCY_VALUE_CAD = "CAD"
        self.CURRENCY_INR = "INR"

        self.SEAL_NUMBER_VALUE = "12345"
        self.NOTE_VALUE = "Automation test note"

        self.OWNER_FREIGHT_METHOD_FLAT_TEXT = "Flat"
        self.OWNER_FREIGHT_AMOUNT_VALUE = "1000"
        self.PAYMENT_TYPE_VALUE = "Advance"
        self.PAYMENT_MODE_VALUE = "Cash"
        self.PAYMENT_AMOUNT_VALUE = "100"

        self.PAYROLL_TYPE_VALUE = "Per Mile"
        self.DELIVERY_DISTANCE_VALUE = "100"
        self.LOADED_DISTANCE_RATE_VALUE = "10"
        self.PAYROLL_TYPE_PERCENTAGE = "Percentage"
        self.PAYROLL_TYPE_VALUEE = "5"

    # ---------------- Helper methods ----------------
    def click(self, xpath):
        self.wait.until(EC.element_to_be_clickable((By.XPATH, xpath))).click()

    def type_text(self, xpath, value):
        element = self.wait.until(EC.visibility_of_element_located((By.XPATH, xpath)))
        element.clear()
        element.send_keys(value)

    def get_text(self, xpath):
        return self.wait.until(EC.visibility_of_element_located((By.XPATH, xpath))).text

    def is_displayed(self, xpath):
        try:
            return self.wait.until(
                EC.visibility_of_element_located((By.XPATH, xpath))
            ).is_displayed()
        except Exception:
            return False

    def select_by_visible_text(self, xpath, value):
        element = self.wait.until(EC.visibility_of_element_located((By.XPATH, xpath)))
        Select(element).select_by_visible_text(value)

    def element_count(self, xpath):
        return len(self.driver.find_elements(By.XPATH, xpath))

    def capture_screen(self, name):
        self.driver.save_screenshot(f"reports/{name}.png")

    def pause(self, seconds):
        time.sleep(seconds)

    def future_date(self, days):
        return (datetime.now() + timedelta(days=days)).strftime("%m/%d/%Y") + " 12:00 AM"

    def random_index(self, size):
        if size <= 0:
            return "1"
        return str(random.randint(1, size))

    def scroll_down(self):
        self.driver.execute_script("window.scrollTo(0, document.body.scrollHeight);")

    # ---------------- Converted methods ----------------
    def enter_customer_details(self):
        try:
            self.pause(1)
            is_order_button_displayed = self.is_displayed(self.PARENT_ORDER_BUTTON)
            self.click(self.PARENT_ORDER_BUTTON)
            self.click(self.NEW_ORDER_XPATH)
            self.click(self.CUSTOMER_NAME_XPATH)
            self.pause(3)

            size_number = self.element_count(self.DROP_DOWN_OPTIONS_XPATH)
            customer_name_random_num = self.random_index(size_number)

            customer_xpath = (
                self.CUSTOMER_RANDOM_NAME_START_PATH
                + customer_name_random_num
                + self.CUSTOMER_RANDOM_NAME_END_PATH
            )
            self.customer_name = self.get_text(customer_xpath)
            self.click(customer_xpath)

            self.click(self.SALESMAN_XPATH)
            size_number = self.element_count(self.DROP_DOWN_OPTIONS_XPATH)
            sales_name_random_num = self.random_index(size_number)

            sales_xpath = (
                self.SALESMAN_RANDOM_NAME_START_PATH
                + sales_name_random_num
                + self.SALSMAN_RANDOM_NAME_END_PATH
            )
            self.sales_name = self.get_text(sales_xpath)
            self.click(sales_xpath)

            self.type_text(self.SALESMAN_COMMISSION_XPATH, self.SALESMAN_COMMISION)
            self.type_text(self.ORDER_NOTES_XPATH, self.ORDER_NOTES_WRITE)
            self.capture_screen("enterCustomerDetails")
            return is_order_button_displayed
        except Exception as e:
            print(f"enter_customer_details failed: {e}")
            return False

    def enter_shipment_details(self):
        try:
            drop_future_date = self.future_date(3)
            is_location_button_displayed = self.is_displayed(self.SHIPMENT_PICKUP_LOCATION_XPATH)

            self.click(self.SHIPMENT_PICKUP_LOCATION_XPATH)
            size_number = self.element_count(self.DROP_DOWN_OPTIONS_XPATH)
            self.click(
                self.SHIPMENT_RANDOM_PICUP_LOCATION_START_PATH
                + self.random_index(size_number)
                + self.SHIPMENT_RANDOM_PICUP_END_PATH
            )

            self.click(self.SHIPMENT_DELIVERY_LOCATION_XPATH)
            size_number = self.element_count(self.DROP_DOWN_OPTIONS_XPATH)
            self.click(
                self.SHIPMENT_RANDOM_DROP_LOCATION_START_PATH
                + self.random_index(size_number)
                + self.SHIPMENT_RANDOM_DROP_LOCATION_END_PATH
            )

            self.type_text(self.DELIVER_DATE_XPATH, drop_future_date)
            self.type_text(
                self.SHIPMENT_REFRENCE_NUMBER_XPATH,
                self.SHIPMENT_REFERENCE_NUMBER_COMMENT,
            )
            self.type_text(self.SHIPMENT_NOTE_XPATH, self.SHIPMENT_NOTE)
            self.click(self.CSA_XPATH)
            self.click(self.HAZMAT_XPATH)
            self.capture_screen("enterShipmentDetails")
            return is_location_button_displayed
        except Exception as e:
            print(f"enter_shipment_details failed: {e}")
            return False

    def enter_revenue_details(self):
        try:
            if self.base_url in [
                "https://afm2020.com/",
                "http://192.168.1.22:2020/",
                "https://qa.afm2020.com/",
            ]:
                self.select_by_visible_text(self.CURRENCY_XPATH, self.CURRENCY_VALUE_CAD)
            elif self.base_url in [
                "http://india.afm2020.com/",
                "http://192.168.1.20:2038",
            ]:
                self.select_by_visible_text(self.CURRENCY_XPATH, self.CURRENCY_INR)

            self.type_text(
                self.REVENUE_FREIGHT_CHARGERATE_XPATH,
                self.REVENUE_FREIGHT_CHARGERATE_VALUE,
            )
            self.select_by_visible_text(
                self.REVENUE_FUEL_RATE_METHOD_XPATH,
                self.REVENUE_RATE_METHOD_FLAT_TEXT,
            )
            self.type_text(self.REVENUE_FUEL_RATE_XPATH, self.REVENUE_FUEL_RATE_VALUE)
            self.click(self.UPDATECUSTOMER_REVENUEDETAILS)
            self.capture_screen("enterRevenueDetails")
            self.scroll_down()
            return self.is_displayed(self.CREATE_TRIP_ORDER)
        except Exception as e:
            print(f"enter_revenue_details failed: {e}")
            return False

    def create_trip(self):
        try:
            self.pause(2)
            is_trip_button_displayed = self.is_displayed(self.CREATE_TRIP_ORDER)
            self.click(self.CREATE_TRIP_ORDER)
            self.capture_screen("createTrip")
            self.trip_number = self.get_text(self.GET_TRIP_NUMBER_XPATH).split(" ")[2]
            return is_trip_button_displayed
        except Exception as e:
            print(f"create_trip failed: {e}")
            return False

    def get_trip_text(self):
        return self.get_text(self.TRIP_VERIFY_XPATH)

    def create_invoice(self):
        try:
            self.pause(2)
            self.click(self.TRIP_PLANNING_XPATH)
            self.order_number = self.get_text(self.GET_ORDER_NUMBER_XPATH)
            self.click(self.INVOICE_ORDER_XPATH)
            is_save_button_displayed = self.is_displayed(self.SAVE_INVOICE)
            self.click(self.SAVE_INVOICE)

            if self.element_count(self.CLOSE_CUSTOMER_EMAIL_SENDPOPUP) > 0:
                self.click(self.CLOSE_CUSTOMER_EMAIL_SENDPOPUP)

            self.capture_screen("createInvoice")
            return is_save_button_displayed
        except Exception as e:
            print(f"create_invoice failed: {e}")
            return False

    def fill_assert_details(self):
        try:
            is_truck_button_displayed = self.is_displayed(self.TRUCK_NUMBER_XPATH)

            self.click(self.TRUCK_NUMBER_XPATH)
            size_number = self.element_count(self.DROP_DOWN_OPTIONS_XPATH)
            self.truck_name = self.random_index(size_number)
            truck_xpath = (
                self.TRUCK_RANDOM_PICUP_LOCATION_START_PATH
                + self.truck_name
                + self.TRUCK_RANDOM_PICUP_END_PATH
            )
            self.truck_reference_name = self.get_text(truck_xpath)
            self.click(truck_xpath)

            if self.is_displayed(self.CONFIRM_TRUCK_NUMBER_ONE):
                self.click(self.CONFIRM_TRUCK_NUMBER_ONE)
                if self.is_displayed(self.CONFIRM_TRUCK_NUMBER_ONE):
                    self.click(self.CONFIRM_TRUCK_NUMBER_ONE)

            self.pause(5)

            self.click(self.DRIVER_NAME_XPATH)
            size_number = self.element_count(self.DROP_DOWN_OPTIONS_XPATH)
            self.driver_name = self.random_index(size_number)
            driver_xpath = self.DRIVER_NUMBER_START_PATH + self.driver_name + self.DRIVER_NUMBER_END_PATH
            self.driver_reference_name = self.get_text(driver_xpath).split(" ")[0]
            self.click(driver_xpath)

            if self.is_displayed(self.DRIVER_NUMBER_CONFIRM):
                self.click(self.DRIVER_NUMBER_CONFIRM)
                if self.is_displayed(self.DRIVER_NUMBER_CONFIRM):
                    self.click(self.DRIVER_NUMBER_CONFIRM)

            self.pause(5)

            self.click(self.DRIVER_TWO_XPATH)
            size_number = self.element_count(self.DROP_DOWN_OPTIONS_XPATH)
            self.driver_two_name = self.random_index(size_number)
            driver_two_xpath = self.DRIVER_TWO_START_PATH + self.driver_two_name + self.DRIVER_TWO_END_PATH
            self.driver_two_reference_name = self.get_text(driver_two_xpath).split(" ")[0]
            self.click(self.DRIVER_NUMBER_START_PATH + self.driver_two_name + self.DRIVER_NUMBER_END_PATH)

            self.pause(2)
            if self.is_displayed(self.DRIVER_NUMBER_CONFIRM):
                self.click(self.DRIVER_NUMBER_CONFIRM)
                if self.is_displayed(self.DRIVER_NUMBER_CONFIRM):
                    self.click(self.DRIVER_NUMBER_CONFIRM)

            self.pause(3)

            self.click(self.TRAIER_NUMBER_XPATH)
            size_number = self.element_count(self.DROP_DOWN_OPTIONS_XPATH)
            self.trailer_name_value = self.random_index(size_number)
            trailer_xpath = self.DRIVER_TWO_START_PATH + self.trailer_name_value + self.DRIVER_TWO_END_PATH
            self.trailer_reference_name = self.get_text(trailer_xpath)
            self.click(self.DRIVER_NUMBER_START_PATH + self.trailer_name_value + self.DRIVER_NUMBER_END_PATH)

            self.pause(2)
            if self.is_displayed(self.DRIVER_NUMBER_CONFIRM):
                self.click(self.DRIVER_NUMBER_CONFIRM)
                if self.is_displayed(self.DRIVER_NUMBER_CONFIRM):
                    self.click(self.DRIVER_NUMBER_CONFIRM)

            self.pause(5)
            self.click(self.ASSETS_DETAILS_SAVE_XPATH)
            self.capture_screen("fillAssertDetails")
            return is_truck_button_displayed
        except Exception as e:
            print(f"fill_assert_details failed: {e}")
            return False

    def verify_assert_details(self):
        return self.get_text(self.DISPATCH_TRIP_XPATH)

    def add_notes(self):
        try:
            self.click(self.ADD_NOTE_XPATH)
            self.type_text(self.NOTE_XPATH, self.NOTE_VALUE)
            self.click(self.SAVE_NOTE_XPATH)
            is_close_note_button_displayed = self.is_displayed(self.CLOSE_NOTE_XPATH)
            self.click(self.CLOSE_NOTE_XPATH)
            self.capture_screen("addNotes")
            return is_close_note_button_displayed
        except Exception as e:
            print(f"add_notes failed: {e}")
            return False

    def truck_name_method(self):
        self.click(self.TRUCK_NUMBER_XPATH)
        size_number = self.element_count(self.DROP_DOWN_OPTIONS_XPATH)
        self.truck_name = self.random_index(size_number)
        truck_xpath = (
            self.TRUCK_RANDOM_PICUP_LOCATION_START_PATH
            + self.truck_name
            + self.TRUCK_RANDOM_PICUP_END_PATH
        )
        self.truck_reference_name = self.get_text(truck_xpath)
        self.click(truck_xpath)

        if self.is_displayed(self.CONFIRM_TRUCK_NUMBER_ONE):
            self.click(self.CONFIRM_TRUCK_NUMBER_ONE)
            if self.is_displayed(self.CONFIRM_TRUCK_NUMBER_ONE):
                self.click(self.CONFIRM_TRUCK_NUMBER_ONE)

        return self.truck_reference_name

    def driver_name_one(self):
        self.pause(5)
        self.click(self.DRIVER_NAME_XPATH)
        size_number = self.element_count(self.DROP_DOWN_OPTIONS_XPATH)
        self.driver_name = self.random_index(size_number)
        driver_xpath = self.DRIVER_NUMBER_START_PATH + self.driver_name + self.DRIVER_NUMBER_END_PATH
        self.driver_reference_name = self.get_text(driver_xpath).split(" ")[0]
        self.click(driver_xpath)

        if self.is_displayed(self.DRIVER_NUMBER_CONFIRM):
            self.click(self.DRIVER_NUMBER_CONFIRM)
            if self.is_displayed(self.DRIVER_NUMBER_CONFIRM):
                self.click(self.DRIVER_NUMBER_CONFIRM)

        return self.driver_reference_name

    def driver_name_two(self):
        self.pause(5)
        self.click(self.DRIVER_TWO_XPATH)
        size_number = self.element_count(self.DROP_DOWN_OPTIONS_XPATH)
        self.driver_two_name = self.random_index(size_number)
        driver_two_xpath = self.DRIVER_TWO_START_PATH + self.driver_two_name + self.DRIVER_TWO_END_PATH
        self.driver_two_reference_name = self.get_text(driver_two_xpath).split(" ")[0]
        self.click(self.DRIVER_NUMBER_START_PATH + self.driver_two_name + self.DRIVER_NUMBER_END_PATH)

        self.pause(2)
        if self.is_displayed(self.DRIVER_NUMBER_CONFIRM):
            self.click(self.DRIVER_NUMBER_CONFIRM)
            if self.is_displayed(self.DRIVER_NUMBER_CONFIRM):
                self.click(self.DRIVER_NUMBER_CONFIRM)

        return self.driver_two_reference_name

    def trailer_name_method(self):
        self.pause(3)
        self.click(self.TRAIER_NUMBER_XPATH)
        size_number = self.element_count(self.DROP_DOWN_OPTIONS_XPATH)
        self.trailer_name_value = self.random_index(size_number)
        trailer_xpath = self.DRIVER_TWO_START_PATH + self.trailer_name_value + self.DRIVER_TWO_END_PATH
        self.trailer_reference_name = self.get_text(trailer_xpath)
        self.click(self.DRIVER_NUMBER_START_PATH + self.trailer_name_value + self.DRIVER_NUMBER_END_PATH)

        self.pause(2)
        if self.is_displayed(self.DRIVER_NUMBER_CONFIRM):
            self.click(self.DRIVER_NUMBER_CONFIRM)
            if self.is_displayed(self.DRIVER_NUMBER_CONFIRM):
                self.click(self.DRIVER_NUMBER_CONFIRM)

        self.type_text(self.SEAL_NUMBER_XPATH, self.SEAL_NUMBER_VALUE)
        self.pause(5)
        self.click(self.ASSETS_DETAILS_SAVE_XPATH)
        self.capture_screen("fillAssertDetails")
        return self.trailer_reference_name

    def trip_journey(self):
        try:
            self.pause(1)
            self.click(self.DISPATCH_TRIP_XPATH)
            self.pause(1)

            if self.element_count(self.CONTINUE_XPATH) > 0:
                self.click(self.CONTINUE_XPATH)
                self.pause(2)

            if self.element_count(self.DISPATCH_TRIP_OK_VERIFIER) > 0:
                self.click(self.DISPATCH_TRIP_OK_WARNING)
                self.pause(2)

                if self.element_count(self.SEND_MESSAGE_DRIVER_XPATH) > 0:
                    self.click(self.SEND_MESSAGE_DRIVER_XPATH)
                    self.pause(2)

            elif self.element_count(self.SEND_MESSAGE_DRIVER_XPATH) > 0:
                self.click(self.SEND_MESSAGE_DRIVER_XPATH)
                self.pause(3)

            if self.element_count(self.START_TRIP_VERIFIER_XPATH) == 0:
                self.click(self.START_TRIP_XPATH)

            self.pause(2)
            self.click(self.QUICK_DELIVER_XPATH)
            self.pause(1)
            is_trip_button_displayed = self.is_displayed(self.TRIP_ASSERTS_STATUS)
            self.click(self.TRIP_ASSERTS_STATUS)
            self.capture_screen("tripJourney")
            return is_trip_button_displayed
        except Exception as e:
            print(f"trip_journey failed: {e}")
            return False

    def settledment_owner(self):
        try:
            self.pause(2)
            self.click(self.SETTLED_XPATH)
            self.pause(2)
            self.select_by_visible_text(self.OWNER_PAYROLL_TYPE_XAPTH, self.OWNER_FREIGHT_METHOD_FLAT_TEXT)
            self.type_text(self.OWNER_FREIGHT_AMOUNT_XPATH, self.OWNER_FREIGHT_AMOUNT_VALUE)
            self.click(self.ADDITION_DEDUCTION_XPATH)
            self.select_by_visible_text(self.PAYMENT_TYPE_XPATH, self.PAYMENT_TYPE_VALUE)
            self.select_by_visible_text(self.PAYMENT_MODE_XPATH, self.PAYMENT_MODE_VALUE)
            self.type_text(self.PAYMENT_AMOUNT_XPATH, self.PAYMENT_AMOUNT_VALUE)
            self.click(self.ADD_XPATH)
            self.scroll_down()
            self.click(self.PAYMENT_GET_XPATH)
            self.click(self.FINAL_OWNER_OPERATOR)
            self.pause(2)
            is_final_confirmation_button_displayed = self.is_displayed(self.FINAL_CONFORMATION_OWNER)
            self.click(self.FINAL_CONFORMATION_OWNER)
            self.pause(5)
            self.click(self.EMAIL_SETTLEMENT_MESSAGE_POPUP)
            self.capture_screen("settledmentOwner")
            return is_final_confirmation_button_displayed
        except Exception as e:
            print(f"settledment_owner failed: {e}")
            return False

    def settlement_driver_one(self):
        try:
            self.pause(3)
            self.click(self.CONTINUE_DRIVER_SETTLEMENT)
            self.click(self.DRIVER_ONE_DEDUCATION_XPATH)
            is_driver_button_displayed = self.is_displayed(self.DRIVER_DEDUCATION_OWNER)
            self.click(self.DRIVER_DEDUCATION_OWNER)
            self.select_by_visible_text(self.OWNER_PAYROLL_TYPE_XAPTH, self.PAYROLL_TYPE_VALUE)
            self.type_text(self.DELIVERY_DISTANCE_XPATH, self.DELIVERY_DISTANCE_VALUE)
            self.type_text(self.LOADED_DISTANCE_RATE, self.LOADED_DISTANCE_RATE_VALUE)
            self.click(self.DRIVER_GET_SETTLEMENT)
            self.click(self.DRIVER_FINAL_SETTLEMENT)
            self.click(self.FINAL_CONFORMATION_DRIVER)
            self.pause(3)
            self.click(self.DRIVER_SETTLEMENT_EMAIL_POPUP)
            self.capture_screen("settlementDriverOne")
            self.pause(1)
            return is_driver_button_displayed
        except Exception as e:
            print(f"settlement_driver_one failed: {e}")
            return False

    def settlement_driver_two(self):
        try:
            self.click(self.DRIVER_SETTLEMENT_CLOSE)
            is_settled_button_displayed = self.is_displayed(self.SETTLED_XPATH)
            self.click(self.SETTLED_XPATH)
            self.click(self.OWNER_SETTLEMENT_OPEN)
            self.click(self.DRIVER_TWO_DEDUCATION_XPATH)
            self.click(self.DRIVER_TWO_DEDUCATION_OWNER)
            self.select_by_visible_text(self.OWNER_PAYROLL_TYPE_XAPTH, self.PAYROLL_TYPE_PERCENTAGE)
            self.click(self.PAYROLL_TYPE_XPATH)
            self.type_text(self.PAYROLL_TYPE_XPATH, self.PAYROLL_TYPE_VALUEE)
            self.click(self.DRIVER_GET_SETTLEMENT)
            self.click(self.DRIVER_FINAL_SETTLEMENT)
            self.click(self.FINAL_CONFORMATION_DRIVER)
            self.pause(3)
            self.click(self.DRIVER_TWO_SETTLEMENT_EMAIL_POPUP)
            self.pause(2)
            return is_settled_button_displayed
        except Exception as e:
            print(f"settlement_driver_two failed: {e}")
            return False

    def payment_truck_owner(self):
        try:
            self.click(self.PAYMENT_XPATH)
            self.click(self.CREATE_PAYMENT_OWNER_XPATH)
            self.click(self.ACCOUNT_NAME_XPATH)

            size_number = self.element_count(self.DROP_DOWN_OPTIONS_XPATH)
            self.truck_owner_payment = self.random_index(size_number)

            account_xpath = self.ACCOUNT_START_PATH + self.truck_owner_payment + self.ACCOUNT_END_PATH
            self.truck_owner_reference = self.get_text(account_xpath).split(" ")[0]
            self.click(account_xpath)

            self.click(self.PAYMENT_METHOD_XPATH)
            size_number = self.element_count(self.PAYMENT_ACCOUNT_XPATH)
            payment_xpath = self.PAYMENT_START_PATH + self.random_index(size_number) + self.PAYMENT_END_PATH
            self.click(payment_xpath)

            is_submit_displayed = self.is_displayed(self.PAYMENT_SUBMIT_XPATH)
            self.click(self.PAYMENT_SUBMIT_XPATH)
            self.pause(5)
            self.click(self.EMAIL_PAYMENT_XPATH)
            self.capture_screen("paymentTruckOwner")
            return is_submit_displayed
        except Exception as e:
            self.capture_screen("paymentTruckOwner")
            print(f"payment_truck_owner failed: {e}")
            return False

    def payment_driver_one(self):
        try:
            is_driver_settlement_button_displayed = self.is_displayed(self.CREATE_PAYMENT_DRIVER_ONE_XPATH)
            self.click(self.CREATE_PAYMENT_DRIVER_ONE_XPATH)
            self.click(self.ACCOUNT_NAME_XPATH)

            size_number = self.element_count(self.PAYMENT_ACCOUNT_XPATH)
            account_xpath = self.ACCOUNT_START_PATH + self.random_index(size_number) + self.ACCOUNT_END_PATH
            self.click(account_xpath)

            self.click(self.PAYMENT_METHOD_XPATH)
            self.pause(1)
            size_number = self.element_count(self.PAYMENT_ACCOUNT_XPATH)
            payment_xpath = self.PAYMENT_START_PATH + self.random_index(size_number) + self.PAYMENT_END_PATH
            self.click(payment_xpath)

            self.click(self.PAYMENT_SUBMIT_XPATH)
            self.pause(5)
            self.click(self.EMAIL_PAYMENT_XPATH)
            self.capture_screen("paymentDriverOne")
            return is_driver_settlement_button_displayed
        except Exception as e:
            self.capture_screen("paymentDriverOne")
            print(f"payment_driver_one failed: {e}")
            return False
