from datetime import datetime

import string
import pytz


class Trigger(object):
    def evaluate(self, story):
        """
        Returns True if an alert should be generated
        for the given news item, or False otherwise.
        """
        # DO NOT CHANGE THIS!
        raise NotImplementedError

# PHRASE TRIGGERS

# Problem 2
# TODO: PhraseTrigger
class PhraseTrigger(Trigger):
    def __init__(self, phrase):
        self.phrase = phrase.lower()
    def is_phrase_in(self, text):
        phrase_words = self.phrase.split(' ')
        for char in string.punctuation:
            text = text.replace(char, ' ')
        text_words = text.lower().split()
        for i in range(len(text_words)):
            if text_words[i] == phrase_words[0]:
                if text_words[i:i+len(phrase_words)] == phrase_words:
                    return True 
                return False
        return False
        

# Problem 3
# TODO: TitleTrigger
class TitleTrigger(PhraseTrigger):
    # def __init__(self, phrase):
    #     PhraseTrigger.__init__(self, phrase)
    def evaluate(self, story):
        return self.is_phrase_in(story.get_title())

# Problem 4
# TODO: DescriptionTrigger
class DescriptionTrigger(PhraseTrigger):
    def evaluate(self, story):
        return self.is_phrase_in(story.get_description())

# TIME TRIGGERS

# Problem 5
# TODO: TimeTrigger
# Constructor:
#        Input: Time has to be in EST and in the format of "%d %b %Y %H:%M:%S".
#        Convert time from string to a datetime before saving it as an attribute.

class TimeTrigger(Trigger):
    def __init__(self, date_time_string):
        date_time = datetime.strptime(date_time_string, "%d %b %Y %H:%M:%S")
        date_time = date_time.replace(tzinfo=pytz.timezone("EST"))
        self.date_time = date_time

# Problem 6
# TODO: BeforeTrigger and AfterTrigger
class BeforeTrigger(TimeTrigger):
    def evaluate(self, story):
        return self.date_time > story.get_pubdate().replace(tzinfo=pytz.timezone("EST"))

class AfterTrigger(TimeTrigger):
    def evaluate(self, story):
        return self.date_time < story.get_pubdate().replace(tzinfo=pytz.timezone("EST"))


# COMPOSITE TRIGGERS

# Problem 7
# TODO: NotTrigger
class NotTrigger(Trigger):
    def __init__(self, other):
        self.other = other
    def evaluate(self, story):
        return not self.other.evaluate(story)

# Problem 8
# TODO: AndTrigger
class AndTrigger(Trigger):
    def __init__(self, trigger1, trigger2):
        self.trigger1 = trigger1
        self.trigger2 = trigger2
    def evaluate(self, story):
        return (self.trigger1.evaluate(story)) and (self.trigger2.evaluate(story))

# Problem 9
# TODO: OrTrigger
class OrTrigger(Trigger):
    def __init__(self, trigger1, trigger2):
        self.trigger1 = trigger1
        self.trigger2 = trigger2
    def evaluate(self, story):
        return (self.trigger1.evaluate(story)) or (self.trigger2.evaluate(story))




def read_trigger_config(filename):
    """
    filename: the name of a trigger configuration file

    Returns: a list of trigger objects specified by the trigger configuration
        file.
    """
    # We give you the code to read in the file and eliminate blank lines and
    # comments. You don't need to know how it works for now!
    trigger_file = open(filename, 'r')
    lines = []
    for line in trigger_file:
        line = line.rstrip()
        if not (len(line) == 0 or line.startswith('//')):
            lines.append(line)

    # TODO: Problem 11
    # line is the list of lines that you need to parse and for which you need
    # to build triggers
    
    # Create a dictionary of trigger names
    trigger_dict = {'TITLE': TitleTrigger, 'DESCRIPTION': DescriptionTrigger,
                    'BEFORE': BeforeTrigger, 'AFTER': AfterTrigger,
                    'NOT': NotTrigger, 'AND': AndTrigger, 'OR': OrTrigger}
    
    trigger_list = []
    triggers = {}
    for line in lines:
        t = line.split(',')
        if t[0] != 'ADD':
            if t[1] == 'NOT':
                triggers[t[0]] = trigger_dict['NOT'](t[2])
            elif (t[1] == 'AND') or (t[1] == 'OR'):
                triggers[t[0]] = trigger_dict[t[1]](t[2], t[3])
            else:
                triggers[t[0]] = trigger_dict[t[1]](t[2])
        else:
            for elem in t[1:]:
                trigger_list.append(triggers[elem])

    print('lines:', lines) # for now, print it so you see what it contains!
    print('triggers', triggers)
    print('triggger_list', trigger_list)
    
read_trigger_config('triggers.txt')