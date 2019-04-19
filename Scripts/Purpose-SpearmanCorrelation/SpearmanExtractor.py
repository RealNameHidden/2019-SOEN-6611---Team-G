from scipy.stats.stats import spearmanr
import pandas as pd
import SpearmanScatterPlot as Pplot
print("Enter sheet name")
sheetname=input()
#Enter the excel file path name below
df = pd.read_excel(r'C:\Users\14387\Desktop\sm\1 ,2 and 4 correlation\jacoco-commons_codec_1.10.xlsx', sheetname)

print(df)

#Enter the coulmn names for xstring and ystring variables as per the excel sheet
xstring='Statement Coverage'
ystring='Complexity'
X = df[xstring]
Y = df[ystring]

print(X)
print(Y)
coefficient, value = spearmanr(X, Y) #this line gets the spearman correlation
coefficient= round(coefficient,2)
value= round(value,4)
print("correlation coefficient=", coefficient, "\n", "2 sided p value=",value)

Pplot.plot(X, Y,xstring,ystring, sheetname, coefficient, value) # Calls Pplot function to plot graph