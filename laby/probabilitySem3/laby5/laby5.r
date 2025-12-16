# zadanie 1
  avgIQ <- 109
  varIQ <- 225
  sampleStudents <- 100
  noStudentMore115 <- 30
  # a) test Z
    #przybliżanie rozkładu dwumianowego dla 100 
    #prób rozkład dla 0.35 jako wartości
    #oczekiwanej. Sprawdzamy jakie jest prawdopodobieństwo na to że wylosujemy 
    #rezultat który rzeczywiście otrzymaliśmy
    probForMore115Hypothesis <- 0.35
    p_a <- 2*pnorm(
      noStudentMore115 / sampleStudents, 
      probForMore115Hypothesis, 
      sqrt(probForMore115Hypothesis * (1 - probForMore115Hypothesis) 
           / sampleStudents)
    )
    # nie trzeba odejmować od 1 bo to już nasz ogon (0.3 wypada przed wartością
    # oczekiwaną 0.35)
    p_a <- prop.test(noStudentMore115, sampleStudents, probForMore115Hypothesis)
  
  
  # b)
    p_b <- 0.005
    qnorm(
      p_b, 
      noStudentMore115 / sampleStudents, 
      probForMore115Hypothesis, 
      sqrt(probForMore115Hypothesis * (1 - probForMore115Hypothesis) 
           / sampleStudents)
    )
    
    
    
    
    
    
    
