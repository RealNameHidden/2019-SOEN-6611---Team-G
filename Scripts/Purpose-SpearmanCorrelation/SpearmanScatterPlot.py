import matplotlib.pyplot as plt


def plot(X, Y,xstring,ystring, sheetname, coefficient, value):
    fig, ax1 = plt.subplots()
    ax1.plot(X, Y, 'bo')
    plt.xlabel(xstring)
    plt.ylabel(ystring)
    plt.title(r'Spearman Correlation'+' R='+str(coefficient)+' P='+str(value),fontweight="bold")
  #  plt.text(0.1, 1,r'r='+str(coefficient))

    plt.savefig(sheetname+"1,4"+"-plot")
    plt.show()
