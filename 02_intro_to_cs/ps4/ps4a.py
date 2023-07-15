# Problem Set 4A
# Name: <your name here>
# Collaborators:
# Time Spent: x:xx

def get_permutations(sequence):
    '''
    Enumerate all permutations of a given string

    sequence (string): an arbitrary string to permute. Assume that it is a
    non-empty string.  

    You MUST use recursion for this part. Non-recursive solutions will not be
    accepted.

    Returns: a list of all permutations of sequence

    Example:
    >>> get_permutations('abc')
    ['abc', 'acb', 'bac', 'bca', 'cab', 'cba']

    Note: depending on your implementation, you may return the permutations in
    a different order than what is listed here.
    '''
    
    # Base case
    if len(sequence) == 1:
        return sequence
    
    # Recursive case
    permutations = []
    for i, char in enumerate(sequence):
        remains = sequence[:i] + sequence[i+1:]
        tail = get_permutations(remains)
        for t in tail:
            permutations.append(char + t)
    return permutations
    
    

if __name__ == '__main__':
    
#    # Put three example test cases here (for your sanity, limit your inputs
#    to be three characters or fewer as you will have n! permutations for a 
#    sequence of length n)

    # Example 1
    example_input1 = 'abc'
    print('Input:', example_input1)    
    print('Expected Output:', ['abc', 'acb', 'bac', 'bca', 'cab', 'cba'])
    print('Actual Output:', get_permutations(example_input1))
    print()

    # Example 2
    example_input2 = 'reds'
    print('Input:', example_input2)    
    print('Expected Output: a list of 24 elements', )
    print('Actual Output:', get_permutations(example_input2))
    print()

    # Example 1
    example_input3 = 'aac'
    print('Input:', example_input3)    
    print('Expected Output:', ['aac', 'aca', 'aac', 'aca', 'caa', 'caa'])
    print('Actual Output:', get_permutations(example_input3))
    print()

