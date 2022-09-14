#Julie Depenyou
#Project 3
#Last edited on 12/12/21 @ 10:24 pm

attach(gss)
library(car)
library(summarytools)
#library(PerformanceAnalytics) # for chart.Correlation
library(pwr)  
library(stargazer)

child <- gss$CHILDS
child <- car::recode(gss$CHILDS,"'EIGHT OR MORE'=8")
child.fix <- as.numeric(as.character(child)) #recode child so that 8 or more is just numeric 8

age <-gss$AGE
age <- car::recode(gss$AGE,"'89 OR OLDER'=89") #recode age so that 89 or older is just 89
age.fix <- as.numeric(as.character(age))

summary(age.fix)
sd(age.fix,na.rm = TRUE)
str(gss$EDUC)
summary(gss$EDUC)
# dummy code the education level: less than HS, HS, more than HS

gss$less_HS <- recode(as.numeric(gss$EDUC), "1:12=1;13:21=0") #less than HS
gss$HS <- recode(as.numeric(gss$EDUC), "0:12=0;13=1;14:21=0") #HS
gss$more_HS <- recode(as.numeric(gss$EDUC), "0:13=0;14:21=1") #HS

#check recode
table(gss$EDUC, gss$less_HS)  
table(gss$EDUC, gss$HS)  
table(gss$EDUC, gss$more_HS) 

#summary(gss$less_HS)
#summarytools::freq(gss$more_HS)

# dummy code for Dwelown: own, rent, other
gss$own <- ifelse(gss$DWELOWN=="OWN OR IS BUYING", 1, 
           ifelse(gss$DWELOWN=="PAYS RENT", 0,
            ifelse(gss$DWELOWN=="OTHER", 0, NA)))
gss$rent <- ifelse(gss$DWELOWN=="OWN OR IS BUYING", 0, 
            ifelse(gss$DWELOWN=="PAYS RENT", 1,
            ifelse(gss$DWELOWN=="OTHER", 0, NA)))
gss$other_h <- ifelse(gss$DWELOWN=="OWN OR IS BUYING", 0, 
            ifelse(gss$DWELOWN=="PAYS RENT", 0,
            ifelse(gss$DWELOWN=="OTHER", 1, NA)))
#summary(gss$DWELOWN)

#check recode
table(gss$DWELOWN, gss$own)  
table(gss$DWELOWN, gss$rent)  
table(gss$DWELOWN, gss$other_h) 

#dummy code for income16: far below avg, below avg, avg, above avg, far above avg
gss$f_b_avg <- recode(as.numeric(gss$INCOM16), "1=1;2:5=0") #far below avg
gss$bel_avg <- recode(as.numeric(gss$INCOM16), "1=0;2=1;3:5=0") #below avg
gss$avg <- recode(as.numeric(gss$INCOM16), "1:2=0;3=1;4:5=0") # avg
gss$ab_avg <- recode(as.numeric(gss$INCOM16), "1:3=0;4=1;5=0") # above avg
gss$f_abv_avg <- recode(as.numeric(gss$INCOM16), "1:4=0;5=1") #far below avg
#summary(gss$INCOM16)
#summarytools::freq(gss$f_abv_avg)


#check recode
table(gss$INCOM16, gss$f_b_avg)  
table(gss$INCOM16, gss$bel_avg)  
table(gss$INCOM16, gss$avg) 
table(gss$INCOM16, gss$ab_avg)  
table(gss$INCOM16, gss$f_abv_avg) 

#run regression model with specifying avg income, renters, and hs education
#as references
reg.child <- lm(child.fix ~ gss$f_b_avg+gss$bel_avg+gss$f_abv_avg+gss$ab_avg
                 +gss$own+gss$other_h+age.fix+gss$less_HS+gss$more_HS
                  ,data = gss)
summary(reg.child)

#check assumption
par(mfrow = c(2,2))  # for viewing all the plots at once in a 2x2 grid
plot(reg.child)
#avPlots(reg.child)

#report findings using stargazer
stargazer(reg.child,type = "html", out= "Downloads/314pr_3.htm")

#multicollinearity
vif(reg.child)

# f-sqr stat
f_sqr = (.2075)/(1-.2075);f_sqr








