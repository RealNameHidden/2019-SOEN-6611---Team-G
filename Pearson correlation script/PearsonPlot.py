import matplotlib.pyplot as plt


def plot(X, Y, sheetname):
    fig, ax1 = plt.subplots()
    ax1.plot(X, Y, 'bo')
    plt.xlabel('Churn/LOC')
    plt.ylabel('Defect Density')
    plt.title('Pearson Correlation')
    plt.savefig(sheetname+"-plot")
    plt.show()
