# IntelliJ tricks

### Content
* [Git Integration](#git-integration)


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