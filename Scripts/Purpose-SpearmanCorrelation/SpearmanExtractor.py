from scipy.stats.stats import spearmanr
import pandas as pd
import SpearmanScatterPlot as Pplot
print("Enter sheet name")
sheetname=input()
df = pd.read_excel(r'C:\Users\14387\Desktop\sm\1 ,2 and 4 correlation\jacoco-commons_codec_1.10.xlsx', sheetname)
#for an earlier version of Excel, you may need to use the file extension of 'xls'
print(df)
xstring='Statement Coverage'
ystring='Complexity'
X = df[xstring]
Y = df[ystring]

print(X)
print(Y)
coefficient, value = spearmanr(X, Y)
coefficient= round(coefficient,2)
value= round(value,4)
print("correlation coefficient=", coefficient, "\n", "2 sided p value=",value)

Pplot.plot(X, Y,xstring,ystring, sheetname, coefficient, value)