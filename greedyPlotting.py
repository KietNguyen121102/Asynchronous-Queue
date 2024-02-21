import pandas as pd
import matplotlib.pyplot as plt
from mpl_toolkits import mplot3d
import numpy as np 



df = pd.read_csv("greedyExpectedCostData.txt", sep = ",")
df.columns = np.arange(1,101)
plt.plot(df.iloc[0])
df.head()

df['1'] = df['1'].str.strip('[').astype(float)
df['100'] = df['100'].str.strip(']').astype(float)
# df = pd.DataFrame(df)

# df.plot(df[0])
y = np.arange(1,101)

# plt.plot(y,df.iloc[0])