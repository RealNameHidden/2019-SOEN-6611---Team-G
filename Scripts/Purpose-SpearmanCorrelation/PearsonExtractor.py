from scipy.stats.stats import pearsonr
import pandas as pd
import PearsonPlot as Pplot
print("Enter sheet name")
sheetname=input()
df = pd.read_excel(r'C:\Users\14387\Desktop\sm\Metric 5 and 6 Data.xlsx', sheetname)
#for an earlier version of Excel, you may need to use the file extension of 'xls'
print (df)

DD = df['Defect/KLOC']
CPL = df['Churn/LOC']
coefficient, value = pearsonr(CPL, DD)
print("correlation coefficient=", coefficient, "\n", "2 sided p value=",value)

Pplot.plot(CPL, DD, sheetname)