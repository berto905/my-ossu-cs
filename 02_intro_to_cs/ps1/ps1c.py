# Enter the starting salary: 150000 
# Best savings rate: 0.4411 
# Steps in bisection search: 12
# It is not possible to pay the down payment in three years.

annual_salary = float(input("Enter your annual salary: "))

semi_annual_raise = 0.07
annual_return = 0.04
portion_down_payment = 0.25
total_cost = 1000000
down_payment = portion_down_payment * total_cost
monthly_salary = (annual_salary)/12

# Initialize the bisection search
low = 0.0
high = 1.0
best = (high + low) / 2
# epsilon = 0.01
steps = 0

# print("Monthly salary", monthly_salary)
def final_saving(monthly_salary, portion_saved):
    current_savings = 0
    current_salary = monthly_salary
    for i in range(36):
        interest = current_savings * (annual_return/12)
        current_savings += interest + (current_salary * portion_saved)
        if ((i > 0) & ((i+1) % 6 == 0)):
            current_salary += current_salary * semi_annual_raise
    return current_savings

# print("Down payment", down_payment)
# print("Maximum saving", final_saving(monthly_salary, high))


if final_saving(monthly_salary, high) < down_payment :
    print("It is not possible to pay the down payment in three years.")

else :
    while True :
        steps += 1
        gap = final_saving(monthly_salary, best) - down_payment
        if gap > 100 :
            high = best
            best = int((high + low)*10000/2) / 10000
        elif gap < -100 :
            low = best
            best = int((high + low)*10000/2) / 10000
        else:
            break
    print("Best savings rate:", best)
    print("Steps in bisection search:", steps)

# while (current_savings < (down_payment - 100) | current_savings:
#     interest = current_savings * (r/12)
#     n = n + 1
#     current_savings += interest + (monthly_salary * portion_saved)
#     if n % 6 == 0 :
#         monthly_salary += monthly_salary * semi_annual_raise

# print("Number of months: " + str(int(n)))

