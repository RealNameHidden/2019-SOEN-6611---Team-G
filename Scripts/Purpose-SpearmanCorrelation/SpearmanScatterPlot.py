import matplotlib.pyplot as plt


def plot(X, Y,xstring,ystring, sheetname, coefficient, value):
    fig, ax1 = plt.subplots()
    ax1.plot(X, Y, 'bo')
    plt.xlabel(xstring)
    plt.ylabel(ystring)
    plt.title(r'Spearman Correlation'+' R='+str(coefficient)+' P='+str(value),fontweight="bold")
  
  #Give the name to the file to be saved as desired below
    plt.savefig(sheetname+"1,4"+"-plot")
    plt.show()
