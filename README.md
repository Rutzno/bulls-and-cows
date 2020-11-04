# bulls-and-cows in Java

 A game with two players where one chose a secret code with 
 some length and containing only unique symbols. The other 
 try to guess it by giving an answer.
 The answer is considered equal to the secret code when the
  number of bulls is equal to the number of secret code, so
   containing zero cow.
 - _**Bulls**: a symbol is considered bull when it appear in the 
 secret code and is in the same position in the secret code._
 - _**Cows**: a symbol is considered cow when it appear in the 
 secret code but is not in the same position in the secret code._  
 
 For instance, assuming the secret code is 5a73 and the response 
 of the other player is :  
 1375: This answer contains 1 bull and 2 cows;  
 35a7: This answer contains 4 cows;  
 fa73: This answer contains 3 bulls;  
 b208: This answer contains aucun bull and cow.  
 
 In the case of this application, we generate automatically 
 a secret code based on the size and number of possible 
 symbols input by the user.
 
