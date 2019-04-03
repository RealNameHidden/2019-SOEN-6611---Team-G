import pandas as pd
import os


def func(path):
    output_file = os.path.join(path, 'Combined.csv')  # path of output file

    # Fetching path for all the files
    complete_file_path = []
    for file_name in os.listdir(path):
        if file_name not in ['desktop.ini', 'Combined.csv']:
            complete_file_path.append(os.path.join(path, file_name))

    print("-----------------------------------------Process Starting for folder--------------------------------")
    print("Total number of files in the folder is : ", len(complete_file_path), '\n')

    total_rows = 0
    dataframe_list = []
    for file_path in complete_file_path:
        print("Loading data for file : ", file_path)
        df = pd.read_csv(file_path, sep=',')
        print("Number of columns  : {0}\nNumber of rows : {1}\n\n".format(df.shape[1], df.shape[0]))
        total_rows += df.shape[0]
        dataframe_list.append(df)

    final = pd.concat(dataframe_list, axis=0, ignore_index=True)
    final.to_csv(output_file, sep=',')
    print("Total rows written : ", total_rows)
    print("-------------Writing to file completed.---------------\n\n")


if __name__ == '__main__':
    root = 'c:\\Files\\DOxico\\'
    sub_dirs = os.listdir(root)
    folder_paths = [os.path.join(root, dirs) for dirs in sub_dirs]
    for folder in folder_paths:
        func(folder)