import tkinter as tk

root =  tk.Tk()
root.title("Ejercicio 10")
root.geometry("500x300")

frame = tk.Frame(root)
frame.pack(fill='both', expand=True)

texto = tk.Text(frame, wrap='word')
texto.insert(tk.END, "Roshar is a world of stone and storms. Uncanny tempests of incredible power sweep across the rocky "
        "terrain so frequently that they have shaped ecology and civilization alike. Animals hide in shells, "
        "trees pull in branches, and grass retracts into the soilless ground. Cities are built only where the "
        "topography offers shelter.\n\n"
        "It has been centuries since the fall of the ten consecrated orders known as the Knights Radiant, but "
        "their Shardblades and Shardplate remain: mystical swords and suits of armor that transform ordinary men "
        "into near-invincible warriors. Men trade kingdoms for Shardblades. Wars are fought for them, and won by them.\n\n"
        "One such war rages on a ruined landscape called the Shattered Plains. There, Kaladin, who traded his "
        "medical apprenticeship for a spear to protect his little brother, has been reduced to slavery. In a war "
        "that makes no sense, where ten armies fight separately against a single foe, he struggles to save his men "
        "and to fathom the leaders who consider them expendable.\n\n"
        "Brightlord Dalinar Kholin commands one of those other armies. Like his brother, the late king, he is "
        "fascinated by an ancient text called The Way of Kings. Troubled by overpowering visions of ancient times "
        "and the Knights Radiant, he has begun to doubt his own sanity.\n\n"
        "Across the ocean, an untried young woman named Shallan seeks to train under the eminent scholar and "
        "notorious heretic Jasnah Kholin, Dalinar's niece. Though she genuinely loves learning, Shallan's motives "
        "are less than pure. As she plans a daring theft, her research for Jasnah hints at secrets of the Knights "
        "Radiant and the true cause of the war.")

texto.grid(row=0, column=0)

scroll = tk.Scrollbar(frame, orient="vertical", command=texto.yview)
scroll.grid(row=0,column=1, sticky="ns")
texto.config(yscrollcommand=scroll.set)

frame.grid_rowconfigure(0, weight=1)
frame.grid_columnconfigure(0, weight=1)




root.mainloop()


