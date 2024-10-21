import tkinter as tk
from tkinter import messagebox



root = tk.Tk()
root.title("Ejercicio 12")
root.geometry("350x450")


def add_user():
   name = name_entry.get()
   age = age_scale.get()
   gender = gender_var.get()


   user_info = f"{name}, Edad: {age}, Género: {gender}"
   user_listbox.insert(tk.END, user_info)
   name_entry.delete(0, tk.END)


def delete_user():
   selected_user = user_listbox.curselection()[0]
   user_listbox.delete(selected_user)


def save_list():
   messagebox.showinfo("Aviso", "La lista ha sido guardada.")


def load_list():
   messagebox.showinfo("Aviso", "La lista ha sido cargada.")






tk.Label(root, text="Nombre: ").grid(row=0, column=0)
name_entry = tk.Entry(root)
name_entry.grid(row=0, column=1)


tk.Label(root, text="Edad: ").grid(pady=15, row=1, column=0)
age_scale = tk.Scale(root, from_=0, to=100, orient=tk.HORIZONTAL)
age_scale.grid(row=1, column=1)


tk.Label(root, text="Género: ").grid(pady=15, row=2, column=0)
gender_var = tk.StringVar(value="Masculino")
tk.Radiobutton(root, text="Masculino", variable=gender_var, value="Masculino").grid(row=2, column=1)
tk.Radiobutton(root, text="Femenino", variable=gender_var, value="Femenino").grid(row=2, column=2)
tk.Radiobutton(root, text="Otro", variable=gender_var, value="Otro").grid(row=2, column=3)


# Botón para añadir usuario
add_button = tk.Button(root, text="Añadir Usuario", command=add_user)
add_button.grid(pady=15, row=3, columnspan=4)


# Lista de usuarios
user_listbox = tk.Listbox(root, width=50)
user_listbox.grid(row=4, columnspan=4)


# Barra de desplazamiento
scrollbar = tk.Scrollbar(root, command=user_listbox.yview)
scrollbar.grid(row=4, column=4, sticky='ns')
user_listbox.config(yscrollcommand=scrollbar.set)


# Botón para eliminar usuario
delete_button = tk.Button(root, text="Eliminar Usuario", command=delete_user)
delete_button.grid(pady=15, row=5, columnspan=4)


# Botón para salir
exit_button = tk.Button(root, text="Salir", command=root.quit)
exit_button.grid(pady=15, row=6, columnspan=4)


# Menú desplegable
menubar = tk.Menu(root)
root.config(menu=menubar)


file_menu = tk.Menu(menubar, tearoff=0)
file_menu.add_command(label="Guardar Lista", command=save_list)
file_menu.add_command(label="Cargar Lista", command=load_list)
menubar.add_cascade(label="Archivo", menu=file_menu)




root.mainloop()
