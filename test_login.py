from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.common.keys import Keys
from webdriver_manager.chrome import ChromeDriverManager
from selenium.webdriver.chrome.service import Service
import time
import random
import string

service = Service(ChromeDriverManager().install())
driver = webdriver.Chrome(service=service)

driver.maximize_window()
driver.get("http://192.168.1.22:2020/")

time.sleep(5)

driver.find_element(By.ID,"txtCorporateId").send_keys("afmqa")
driver.find_element(By.ID,"txtUserName").send_keys("admin")
driver.find_element(By.ID,"txtPassword").send_keys("12345")

driver.find_element(By.ID,"signin").click()

time.sleep(5)

print("Login Successful")

driver.quit()