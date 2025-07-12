# IntelliJ tricks

### Content
* [Git Integration](#git-integration)
* [Hot keys](#hot-keys)


### Git Integration
There are 2 ways to combine Intellij with git:
* using PAT (personal access token) - you have to generate PAT in your Github/Gitlab account and add it to Intellij (setting => version control => Github/Gitlab => add token). Then you can clone repository using SSH link and then you can pull/push changes to the repository. 
```shell
* using SSH key - you have to generate SSH key and add it to your Github/Gitlab account. But the trick here is that you need to launch your IntelliJ with shh key context. If you just launch IntelliJ from the dock, it will not have access to your SSH key and when you try to pull/push you will get error like `Permission denied (publickey)`.
```shell
# go to console and run ssh-agent
eval $(ssh-agent -s)
# add SSH key to ssh-agent
ssh-add ~/.ssh/work_station
# launch IntelliJ from the console, so it have SSH key context
open /Applications/IntelliJ\ IDEA\ CE.app
```

### Hot keys
Below I will list all usable MacOS hot keys for IntelliJ.
[Full list](https://www.jetbrains.com/help/idea/reference-keymap-mac-default.html)

General:
* `⌘ + ,` - open settings
* `⌘ + ;` - open project structure
* `^ + ⌥ + N` - create new file/directory/class/package in the current directory
* `⌘ + N` - generate code (getters, setters, constructors, etc.)
* `^ + T` - refactor code (rename, change signature, etc.)
* `^ + ⌥ + L` - reformat code

Editing:
* `⌘ + Shift + V` - paste from recent buffers

Build:
* `⌘ + fn + F9` - build project (we add `fn` key because on MacOS `F9` is used to go to next track in music player)
* `⌘ + Shift + fn + F9` - build project and run it

Search:
* `⌘ + F` - find in the current file
* `⌘ + R` - replace in the current file
* `⌘ + Shift + F` - find in path
* `⌘ + Shift + R` - replace in path
* `Shift + Shift` - search everywhere
* `⌘ + O` - search class
* `⌘ + Shift + O` - search file
* `⌘ + Shift + A` - search action
* `⌘ + ⌥ + O` - search symbol
* `⌘ + E` - show recently opened files

Code navigation:
* `⌘ + [` - go to previous code location
* `⌘ + ]` - go to next code location
