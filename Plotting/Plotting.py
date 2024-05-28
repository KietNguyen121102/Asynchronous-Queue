import pandas as pd
import matplotlib.pyplot as plt

df = pd.read_csv("Data.txt", header = None, names = ["online","physical","processingTime","arrivalRate","alpha","numCustomers","allQueue","SPE","greedy","random","allLine"])
df['online'] = df['online'].str.strip('[').astype(float)
df['allLine'] = df['allLine'].str.strip(']').astype(float)
df['ratio between SPE and Greedy'] = df['SPE']/df['greedy']

plt.plot(df['ratio between SPE and Greedy'], df['alpha'])

print(df)



plt.show()

