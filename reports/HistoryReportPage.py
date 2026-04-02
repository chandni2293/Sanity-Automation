# account_report_page.py

import os
import time
from datetime import datetime, timedelta

from selenium.webdriver.common.by import By
from selenium.webdriver.support.ui import WebDriverWait, Select
from selenium.webdriver.support import expected_conditions as EC
	
from selenium import webdriver
from account_report_page import AccountReportPage

driver = webdriver.Chrome()
driver.get("https://afm2020.com/")

report_page = AccountReportPage(driver)

assert report_page.profit_and_loss_report()
assert report_page.profit_and_loss_report_print()
assert report_page.profit_and_loss_report_email()

class AccountReportPage:
    def __init__(self, driver):
        self.driver = driver
        self.wait = WebDriverWait(driver, 20)

        # =========================
        # Replace with actual XPaths
        # =========================
        self.REPORTS_XPATH = "your_reports_xpath"
        self.ACCOUNT_XPATH = "your_account_xpath"

        self.PROFIT_LOSS_XPATH = "your_profit_loss_xpath"
        self.PROFIT_ADVANCE_SEARCH_XPATH = "your_profit_advance_search_xpath"
        self.PROFIT_CURRENCY_XPATH = "your_profit_currency_xpath"
        self.PROFIT_DATE_XPATH = "your_profit_date_xpath"

        self.CHEQUE_REGISTER_XPATH = "your_cheque_register_xpath"
        self.CHEQUE_REGISTER_ADVANCE_SEARCH_XPATH = "your_cheque_register_advance_search_xpath"
        self.CHEQUE_REGISTER_DATE_XPATH = "your_cheque_register_date_xpath"

        self.BANK_STATEMENT_XPATH = "your_bank_statement_xpath"
        self.BANK_ACCOUNT_XPATH = "your_bank_account_xpath"
        self.BANK_STATEMENT_DATE_XPATH = "your_bank_statement_date_xpath"

        self.SUBMIT_XPATH = "your_submit_xpath"
        self.REPORT_PRINT_XPATH = "your_report_print_xpath"
        self.REFRESH_REPORT_XPATH = "your_refresh_report_xpath"

        self.EMAIL_BUTTON_XPATH = "//*[contains(@onclick,'EmailAFMReports')]"
        self.EMAIL_TO_XPATH = "your_email_to_xpath"
        self.EMAIL_SEND_XPATH = "your_email_send_xpath"

        # =========================
        # Replace with actual values
        # =========================
        self.currency_value_cad = "CAD"
        self.email_to_value = "test@example.com"
        self.carrier_name = "your_carrier_name"

        os.makedirs("reports", exist_ok=True)

    # -------------------------
    # Generic helpers
    # -------------------------
    def click(self, xpath: str):
        self.wait.until(EC.element_to_be_clickable((By.XPATH, xpath))).click()

    def enter_text(self, xpath: str, value: str):
        element = self.wait.until(EC.visibility_of_element_located((By.XPATH, xpath)))
        element.clear()
        element.send_keys(value)

    def is_displayed(self, xpath: str) -> bool:
        try:
            return self.wait.until(
                EC.visibility_of_element_located((By.XPATH, xpath))
            ).is_displayed()
        except Exception:
            return False

    def select_visible_text(self, xpath: str, text: str):
        element = self.wait.until(EC.visibility_of_element_located((By.XPATH, xpath)))
        Select(element).select_by_visible_text(text)

    def capture_screen(self, file_name: str):
        self.driver.save_screenshot(f"reports/{file_name}.png")

    @staticmethod
    def get_future_date(days: int = 5) -> str:
        return (datetime.now() + timedelta(days=days)).strftime("%m/%d/%Y") + " 12:00 AM"

    # -------------------------
    # Profit & Loss Report
    # -------------------------
    def profit_and_loss_report(self) -> bool:
        try:
            future_date = self.get_future_date(5)

            self.click(self.REPORTS_XPATH)
            self.click(self.ACCOUNT_XPATH)
            self.click(self.PROFIT_LOSS_XPATH)
            self.click(self.PROFIT_ADVANCE_SEARCH_XPATH)
            self.select_visible_text(self.PROFIT_CURRENCY_XPATH, self.currency_value_cad)
            self.enter_text(self.PROFIT_DATE_XPATH, future_date)

            is_submit_visible = self.is_displayed(self.SUBMIT_XPATH)
            self.click(self.SUBMIT_XPATH)
            return is_submit_visible

        except Exception as e:
            print(f"profit_and_loss_report failed: {e}")
            return False

    def profit_and_loss_report_print(self) -> bool:
        try:
            self.click(self.REPORT_PRINT_XPATH)
            is_refresh_visible = self.is_displayed(self.REFRESH_REPORT_XPATH)
            self.click(self.REFRESH_REPORT_XPATH)
            self.capture_screen("profitAndLossReportPrint")
            return is_refresh_visible

        except Exception as e:
            print(f"profit_and_loss_report_print failed: {e}")
            return False

    def profit_and_loss_report_email(self) -> bool:
        try:
            email_btn = self.wait.until(
                EC.presence_of_element_located((By.XPATH, self.EMAIL_BUTTON_XPATH))
            )
            self.driver.execute_script("arguments[0].click();", email_btn)

            self.enter_text(self.EMAIL_TO_XPATH, self.email_to_value)
            is_send_visible = self.is_displayed(self.EMAIL_SEND_XPATH)
            self.click(self.EMAIL_SEND_XPATH)
            self.capture_screen("profitAndLossReportEmail")
            time.sleep(3)
            return is_send_visible

        except Exception as e:
            print(f"profit_and_loss_report_email failed: {e}")
            return False

    # -------------------------
    # Cheque Register Report
    # -------------------------
    def cheque_register_report(self) -> bool:
        try:
            future_date = self.get_future_date(5)

            self.click(self.ACCOUNT_XPATH)
            self.click(self.CHEQUE_REGISTER_XPATH)
            self.click(self.CHEQUE_REGISTER_ADVANCE_SEARCH_XPATH)
            self.enter_text(self.CHEQUE_REGISTER_DATE_XPATH, future_date)

            is_submit_visible = self.is_displayed(self.SUBMIT_XPATH)
            self.click(self.SUBMIT_XPATH)
            return is_submit_visible

        except Exception as e:
            print(f"cheque_register_report failed: {e}")
            return False

    def cheque_register_report_print(self) -> bool:
        try:
            self.click(self.REPORT_PRINT_XPATH)
            is_refresh_visible = self.is_displayed(self.REFRESH_REPORT_XPATH)
            self.click(self.REFRESH_REPORT_XPATH)
            self.capture_screen("chequeRegisterReportPrint")
            return is_refresh_visible

        except Exception as e:
            print(f"cheque_register_report_print failed: {e}")
            return False

    def cheque_register_report_email(self) -> bool:
        try:
            email_btn = self.wait.until(
                EC.presence_of_element_located((By.XPATH, self.EMAIL_BUTTON_XPATH))
            )
            self.driver.execute_script("arguments[0].click();", email_btn)

            self.enter_text(self.EMAIL_TO_XPATH, self.email_to_value)
            is_send_visible = self.is_displayed(self.EMAIL_SEND_XPATH)
            self.click(self.EMAIL_SEND_XPATH)
            self.capture_screen("chequeRegisterReportEmail")
            time.sleep(3)
            return is_send_visible

        except Exception as e:
            print(f"cheque_register_report_email failed: {e}")
            return False

    # -------------------------
    # Bank Statement Report
    # -------------------------
    def bank_statement_report(self) -> bool:
        try:
            future_date = self.get_future_date(5)

            self.click(self.ACCOUNT_XPATH)
            is_bank_stmt_visible = self.is_displayed(self.BANK_STATEMENT_XPATH)
            self.click(self.BANK_STATEMENT_XPATH)
            self.select_visible_text(self.BANK_ACCOUNT_XPATH, self.carrier_name)
            self.enter_text(self.BANK_STATEMENT_DATE_XPATH, future_date)

            is_submit_visible = self.is_displayed(self.SUBMIT_XPATH)
            self.click(self.SUBMIT_XPATH)
            self.capture_screen("bankStatementReport")
            return is_bank_stmt_visible and is_submit_visible

        except Exception as e:
            print(f"bank_statement_report failed: {e}")
            return False

    def bank_statement_report_print(self) -> bool:
        try:
            self.click(self.REPORT_PRINT_XPATH)
            is_refresh_visible = self.is_displayed(self.REFRESH_REPORT_XPATH)
            self.click(self.REFRESH_REPORT_XPATH)
            self.capture_screen("bankStatementReportPrint")
            return is_refresh_visible

        except Exception as e:
            print(f"bank_statement_report_print failed: {e}")
            return False

    def bank_statement_report_email(self) -> bool:
        try:
            email_btn = self.wait.until(
                EC.presence_of_element_located((By.XPATH, self.EMAIL_BUTTON_XPATH))
            )
            self.driver.execute_script("arguments[0].click();", email_btn)

            self.enter_text(self.EMAIL_TO_XPATH, self.email_to_value)
            is_send_visible = self.is_displayed(self.EMAIL_SEND_XPATH)
            self.click(self.EMAIL_SEND_XPATH)
            self.capture_screen("bankStatementReportEmail")
            time.sleep(3)
            return is_send_visible

        except Exception as e:
            print(f"bank_statement_report_email failed: {e}")
            return False
