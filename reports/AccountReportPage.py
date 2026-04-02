from selenium.webdriver.common.by import By
from selenium.webdriver.support.ui import WebDriverWait, Select
from selenium.webdriver.support import expected_conditions as EC
from selenium.common.exceptions import Exception as SeleniumException
from selenium.webdriver.common.action_chains import ActionChains
from datetime import datetime, timedelta
import time


class AccountReportPage:
    def __init__(self, driver):
        self.driver = driver
        self.wait = WebDriverWait(driver, 20)

        # Replace these with your actual locators from constants
        self.REPORTS_XPATH = "your_reports_xpath"
        self.ACCOUNT_XPATH = "your_account_xpath"
        self.PROFIT_LOSS_XPATH = "your_profit_loss_xpath"
        self.PROFIT_ADVANCE_SEARCH_XPATH = "your_profit_advance_search_xpath"
        self.PROFIT_CURRENCY_XPATH = "your_profit_currency_xpath"
        self.PROFIT_DATE_XPATH = "your_profit_date_xpath"
        self.SUBMIT_XPATH = "your_submit_xpath"
        self.REPORT_PRINT_XPATH = "your_report_print_xpath"
        self.REFRESH_REPORT_XPATH = "your_refresh_report_xpath"
        self.EMAIL_TO_XPATH = "your_email_to_xpath"
        self.EMAIL_SEND_XPATH = "your_email_send_xpath"

        self.CHEQUE_REGISTER_XPATH = "your_cheque_register_xpath"
        self.CHEQUE_REGISTER_ADVANCE_SEARCH_XPATH = "your_cheque_register_advance_search_xpath"
        self.CHEQUE_REGISTER_DATE_XPATH = "your_cheque_register_date_xpath"

        self.BANK_STATEMENT_XPATH = "your_bank_statement_xpath"
        self.BANK_ACCOUNT_XPATH = "your_bank_account_xpath"
        self.BANK_STATEMENT_DATE_XPATH = "your_bank_statement_date_xpath"

        self.EMAIL_BUTTON_XPATH = "//*[contains(@onclick,'EmailAFMReports')]"

        # Dynamic value from your test setup
        self.carrier_name = "your_carrier_name"
        self.email_to_value = "test@example.com"
        self.currency_value_cad = "CAD"

    def _click(self, xpath):
        self.wait.until(EC.element_to_be_clickable((By.XPATH, xpath))).click()

    def _type(self, xpath, value):
        element = self.wait.until(EC.visibility_of_element_located((By.XPATH, xpath)))
        element.clear()
        element.send_keys(value)

    def _is_displayed(self, xpath):
        try:
            return self.wait.until(
                EC.visibility_of_element_located((By.XPATH, xpath))
            ).is_displayed()
        except:
            return False

    def _select_by_visible_text(self, xpath, text):
        dropdown = self.wait.until(EC.visibility_of_element_located((By.XPATH, xpath)))
        Select(dropdown).select_by_visible_text(text)

    def _capture_screen(self, name):
        self.driver.save_screenshot(f"reports/{name}.png")

    def _future_date(self, days=5):
        return (datetime.now() + timedelta(days=days)).strftime("%m/%d/%Y") + " 12:00 AM"

    def profit_and_loss_report(self):
        try:
            drop_future_date = self._future_date(5)
            self._click(self.REPORTS_XPATH)
            self._click(self.ACCOUNT_XPATH)
            self._click(self.PROFIT_LOSS_XPATH)
            self._click(self.PROFIT_ADVANCE_SEARCH_XPATH)
            self._select_by_visible_text(self.PROFIT_CURRENCY_XPATH, self.currency_value_cad)
            self._type(self.PROFIT_DATE_XPATH, drop_future_date)
            is_submit_button_displayed = self._is_displayed(self.SUBMIT_XPATH)
            self._click(self.SUBMIT_XPATH)
            return is_submit_button_displayed
        except Exception as e:
            print(f"profit_and_loss_report failed: {e}")
            return False

    def profit_and_loss_report_print(self):
        try:
            self._click(self.REPORT_PRINT_XPATH)
            is_refresh_button_displayed = self._is_displayed(self.REFRESH_REPORT_XPATH)
            self._click(self.REFRESH_REPORT_XPATH)
            self._capture_screen("profitAndLossReportPrint")
            return is_refresh_button_displayed
        except Exception as e:
            print(f"profit_and_loss_report_print failed: {e}")
            return False

    def profit_and_loss_report_email(self):
        try:
            email_btn = self.wait.until(
                EC.presence_of_element_located((By.XPATH, self.EMAIL_BUTTON_XPATH))
            )
            self.driver.execute_script("arguments[0].click();", email_btn)
            self._type(self.EMAIL_TO_XPATH, self.email_to_value)
            is_email_send_button_displayed = self._is_displayed(self.EMAIL_SEND_XPATH)
            self._click(self.EMAIL_SEND_XPATH)
            self._capture_screen("profitAndLossReportEmail")
            time.sleep(3)
            return is_email_send_button_displayed
        except Exception as e:
            print(f"profit_and_loss_report_email failed: {e}")
            return False

    def cheque_register_report(self):
        try:
            drop_future_date = self._future_date(5)
            self._click(self.ACCOUNT_XPATH)
            self._click(self.CHEQUE_REGISTER_XPATH)
            self._click(self.CHEQUE_REGISTER_ADVANCE_SEARCH_XPATH)
            self._type(self.CHEQUE_REGISTER_DATE_XPATH, drop_future_date)
            is_submit_button_displayed = self._is_displayed(self.SUBMIT_XPATH)
            self._click(self.SUBMIT_XPATH)
            return is_submit_button_displayed
        except Exception as e:
            print(f"cheque_register_report failed: {e}")
            return False

    def cheque_register_report_print(self):
        try:
            self._click(self.REPORT_PRINT_XPATH)
            is_refresh_button_displayed = self._is_displayed(self.REFRESH_REPORT_XPATH)
            self._click(self.REFRESH_REPORT_XPATH)
            self._capture_screen("chequeRegisterReportPrint")
            return is_refresh_button_displayed
        except Exception as e:
            print(f"cheque_register_report_print failed: {e}")
            return False

    def cheque_register_report_email(self):
        try:
            email_btn = self.wait.until(
                EC.presence_of_element_located((By.XPATH, self.EMAIL_BUTTON_XPATH))
            )
            self.driver.execute_script("arguments[0].click();", email_btn)
            self._type(self.EMAIL_TO_XPATH, self.email_to_value)
            is_email_send_button_displayed = self._is_displayed(self.EMAIL_SEND_XPATH)
            self._click(self.EMAIL_SEND_XPATH)
            self._capture_screen("chequeRegisterReportEmail")
            time.sleep(3)
            return is_email_send_button_displayed
        except Exception as e:
            print(f"cheque_register_report_email failed: {e}")
            return False

    def bank_statement_report(self):
        try:
            drop_future_date = self._future_date(5)
            self._click(self.ACCOUNT_XPATH)
            is_bank_statement_button_displayed = self._is_displayed(self.BANK_STATEMENT_XPATH)
            self._click(self.BANK_STATEMENT_XPATH)
            self._select_by_visible_text(self.BANK_ACCOUNT_XPATH, self.carrier_name)
            self._type(self.BANK_STATEMENT_DATE_XPATH, drop_future_date)
            is_submit_button_displayed = self._is_displayed(self.SUBMIT_XPATH)
            self._click(self.SUBMIT_XPATH)
            self._capture_screen("bankStatementReport")
            return is_bank_statement_button_displayed and is_submit_button_displayed
        except Exception as e:
            print(f"bank_statement_report failed: {e}")
            return False

    def bank_statement_report_print(self):
        try:
            self._click(self.REPORT_PRINT_XPATH)
            is_refresh_button_displayed = self._is_displayed(self.REFRESH_REPORT_XPATH)
            self._click(self.REFRESH_REPORT_XPATH)
            self._capture_screen("bankStatementReportPrint")
            return is_refresh_button_displayed
        except Exception as e:
            print(f"bank_statement_report_print failed: {e}")
            return False

    def bank_statement_report_email(self):
        try:
            email_btn = self.wait.until(
                EC.presence_of_element_located((By.XPATH, self.EMAIL_BUTTON_XPATH))
            )
            self.driver.execute_script("arguments[0].click();", email_btn)
            self._type(self.EMAIL_TO_XPATH, self.email_to_value)
            is_email_send_button_displayed = self._is_displayed(self.EMAIL_SEND_XPATH)
            self._click(self.EMAIL_SEND_XPATH)
            self._capture_screen("bankStatementReportEmail")
            time.sleep(3)
            return is_email_send_button_displayed
        except Exception as e:
            print(f"bank_statement_report_email failed: {e}")
            return False
