# Enter your annual salary: 120000 
# Enter the percent of your salary to save, as a decimal: . 10 
# Enter the cost of your dream home: 1000000 
# Enter the semiannual raise, as a decimal: .03
# Number of months: 183
# Write a program to calculate how many months it will take you 
# to save up enough money for a down payment.

# import numpy as np

annual_salary = float(input("Enter your annual salary: "))
portion_saved = float(input("Enter the percent of your salary to save, as a decimal: "))
total_cost = float(input("Enter the cost of your dream home: "))
semi_annual_raise = float(input("Enter the semiannual raise, as a decimal: "))

portion_down_payment = 0.25
r = 0.04
monthly_salary = (annual_salary)/12
current_savings = 0

n = 0
while current_savings < (total_cost * portion_down_payment):
    interest = current_savings * (r/12)
    n = n + 1
    current_savings += interest + (monthly_salary * portion_saved)
    if n % 6 == 0 :
        monthly_salary += monthly_salary * semi_annual_raise

print("Number of months: " + str(int(n)))

