#Julie Depenyou
gen_ice <- matrix(c(43,153,78,109,111,29), ncol = 2, byrow = F) #put data in a matrix
gen_ice
rownames(gen_ice) <- c("US","Europe","Asia") #name the rows  
colnames(gen_ice) <-c("Young","Old") #name the cols
gen_ice
chisq.test(gen_ice) #run the chi.square test on the gen_ice bivariable table
chi.gen_ice <- chisq.test(gen_ice) #save chi.square result as an objet

gif_matrix <- matrix(c(65,75,33,32,22,34,1,1,30), ncol = 3, byrow = F) #put data in matrix
gif_matrix
rownames(gif_matrix) <- c("USA","Europe","Asia") #give matrix row names
colnames(gif_matrix) <- c("Hard g","Soft g","All letters") #give matrix col names
gif_matrix
chisq.test(gif_matrix) #run chi.square test on gif_matrix bivariable table
chi.gif_matrix <- chisq.test(gif_matrix) #save chi.square result an an object

