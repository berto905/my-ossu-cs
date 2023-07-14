# Enter your annual salary: 120000 
# Enter the percent of your salary to save, as a decimal: . 10 
# Enter the cost of your dream home: 1000000 
# Number of months: 183
# Write a program to calculate how many months it will take you 
# to save up enough money for a down payment.

# import numpy as np

annual_salary = float(input("Enter your annual salary: "))
portion_saved = float(input("Enter the percent of your salary to save, as a decimal: "))
total_cost = float(input("Enter the cost of your dream home: "))

portion_down_payment = 0.25
r = 0.04
monthly_salary = (annual_salary)/12
current_savings = 0

n = 0
while current_savings < (total_cost * portion_down_payment):
    interest = current_savings * (r/12)
    n = n + 1
    current_savings += interest + (monthly_salary * portion_saved)

print("Number of months: " + str(int(n)))

