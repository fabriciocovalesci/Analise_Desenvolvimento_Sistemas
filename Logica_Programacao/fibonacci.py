# A série de Fibonacci tem como dados os 2 primeiros termos da série que são respectivamente 0 e 1. À partir
# deles, os demais termos são construídos pela seguin + anterior

def fibonacci(n):
	a,b = 0,1
	while b < n:
		print(b)
		a,b = b, a+b

fibonacci(200)


