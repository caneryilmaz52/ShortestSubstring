# ShortestSubstring
---

### Description
---
Given a string comprised of lowercase letters in the range ascii[a-z], determine the length of the smallest substring that contains all of the letters present in the string.

For example, given the string s = dabbcabcd, the list of all characters in the string is [a, b, c, d]. Two of the substrings that contain all letters are dabbc and abcd. The shortest substring containing all the letters is 4 characters long, abcd.

#### Function Description
Complete the function shortestSubstring in the editor below. The function must return the length of the shortest substring that contains all of the characters within s.

shortestSubstring has the following parameter:
s: a string

#### Constraints
- 1 ≤ size of s ≤ 10⁵
- s[i] ∈ ascii['a'-'z']

### Sample Case 0
**Sample Input For Custom Testing**  
bab  
**Sample Output**  
2  
**Explanation**  
"ba" is a substring that contains all the characters in s.  

### Sample Case 1
**Sample Input For Custom Testing**  
bcaacbc  
**Sample Output**  
3  
**Explanation**  
"bca" is a substring that contains all the characters in s.

---

## shortestSubstring function
```kotlin
private fun shortestSubstring(parameterString: String): Int {
        val stringLength: Int = parameterString.length
        val stringCharCount: Int = charCount(parameterString, stringLength)
        var smallestLength = stringLength

        for (i in 0..stringLength) {
            for (j in 0..stringLength) {
                val substring: String = if (i < j) parameterString.substring(i, j) else parameterString.substring(j, i)

                val substringLength = substring.length
                val substringCharCount: Int = charCount(substring, substringLength)

                if (substringLength < smallestLength && stringCharCount == substringCharCount) {
                    smallestLength = substringLength
                }
            }
        }
        return smallestLength
    }

```

---

## charCount function
```kotlin
private val characterLimit = 256
    private fun charCount(string: String, length: Int): Int {
        val count = IntArray(characterLimit)

        for (i in 0 until length) {
            count[string[i].toInt()]++
        }

        var charCount = 0
        for (i in 0 until characterLimit) {
            if (count[i] != 0) {
                charCount++
            }
        }
        return charCount
    }

```
