require_relative './hangman'

class ExtendedGuessTheWordGame < GuessTheWordGame
    ##
end

class ExtendedSecretWord < SecretWord
    def initialize word
        self.word = word
      #  self.pattern = 
    end
end

## Change to 'false' to run the original game
if true
    ExtendedGuessTheWordGame.new(ExtendedSecretWord).play
else
    GuessTheWordGame.new(SecretWord).play
end
