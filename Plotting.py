import pandas as pd
import matplotlib.pyplot as plt

df = pd.read_csv("preferenceData0.35.txt", sep = ",")
df = pd.DataFrame(df)
df.columns = ["onlineBarista","physicalBarista", "processingTime", "arrivalRate", "alpha", "physicalPopulation", "totalCost"]


df['totalCost'] = df['totalCost'].str.strip(']').astype(float)
df['onlineBarista'] = df['onlineBarista'].str.strip('[').astype(float)


pd.to_numeric(df['onlineBarista'])
pd.to_numeric(df['physicalBarista'])

plt.plot(df['onlineBarista'], df['totalCost'])
plt.show()

for i in range(0,6):
        df_plot = df[df['onlineBarista'] == i]
        df_plot = df_plot[df_plot['physicalBarista'] == 5-i]
        print("df_plot dataframe is below")
        print(df_plot)
       
        x = df_plot['physicalPopulation']
        y = df_plot['totalCost']
        plt.plot(x,y, label = str(i) + " onlineBarista, " + str(5-i) + "physicalBarista")
        plt.legend(loc="upper left", fontsize = "5")
        plt.xlabel('physicalPopulation')
        plt.ylabel('totalCost')
        #plt.title("Online barista: " + str(i) + ", Physical barista: " + str(5-i))
plt.show()

        
       

print(df.dtypes)

