import pandas as pd
import matplotlib.pyplot as plt
from mpl_toolkits import mplot3d

df = pd.read_csv("populationData0.75.txt", sep = ",")
df = pd.DataFrame(df)
df.columns = ["onlineBarista","physicalBarista", "processingTime", "arrivalRate", "alpha", "physicalPopulation", "onlinePopulation", "greedyPopulation" , "totalCost"]


df['totalCost'] = df['totalCost'].str.strip(']').astype(float)
df['onlineBarista'] = df['onlineBarista'].str.strip('[').astype(float)


pd.to_numeric(df['onlineBarista'])
pd.to_numeric(df['physicalBarista'])

plt.plot(df['onlineBarista'], df['totalCost'])
plt.show()

for i in range(0,6):
        df_plot = df[df['onlineBarista'] == i]
        df_plot = df_plot[df_plot['physicalBarista'] == 5-i]
        df_plot = df_plot[df_plot['physicalPopulation'] == df_plot['onlinePopulation']]
        print("df_plot dataframe is below")
        print(df_plot)
       
        # fig = plt.figure()
        # ax = plt.axes(projection='3d')

        # xline = df_plot['physicalPopulation']
        # yline = df_plot['greedyPopulation']
        # zline = df_plot['totalCost']
        # plt.legend(loc="upper left", fontsize = "5")
        # plt.title("Physical barista: " + str(5-i) +", Online barista: " + str(i))
        # ax.plot3D(xline, yline, zline, 'red')
        # plt.show()

        x = df_plot['greedyPopulation']
        y = df_plot['totalCost']
        plt.plot(x,y, label = str(i) + " onlineBarista, " + str(5-i) + "physicalBarista")
        plt.legend(loc="upper left", fontsize = "5")
        plt.xlabel('greedyPopulation')
        plt.ylabel('totalCost')
plt.show()
      
       
        #plt.title("Online barista: " + str(i) + ", Physical barista: " + str(5-i))



        
       

print(df.dtypes)

