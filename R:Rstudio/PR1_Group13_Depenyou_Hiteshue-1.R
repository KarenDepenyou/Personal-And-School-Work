#Julie Depenyou
#Ethan Hiteshue
#INST 314 Project 1
#Last revised: October 24 2021


#library("readxl")
#proj1 <- read_excel("Mental Health in Tech Survey (Responses).xlsx")
#load(file.choose())
#source(file = "Mental Health In Tech.xlsx")
#proj1 <- readRDS("Mental Health In Tech.xlsx")
#df=read.csv("Mental Health in Tech Survey (Responses) - Form Responses 1.tsv", sep='\t')

# As you can see above we attemped many times to load the file into r but 
# kept getting errors. We resulted to doing most of our analysis
# on excel and then upload enough data here on r to allow us to conudct 
# the chi-square tes of independence and Cramer's V


prj1 <- matrix(c(219,233,245,220,173,170),ncol = 3, byrow = F)
prj1
rownames(prj1) <- c("Yes","No")
colnames(prj1) <- c("Small","Medium", "Large")
prj1
chisq.test(prj1)
chi.prj1 <- chisq.test(prj1)
install.packages("DescTools")
library(DescTools)
DescTools::CramerV(prj1)

summarytools::freq(prj1)