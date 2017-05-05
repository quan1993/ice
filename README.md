# ice
安装：
第一步，基于Windows下的安装，所以下载windows版的Ice:
http://www.zeroc.com/download.html
      第二步，安装Ice：
常规安装即可，可以选择安装目录，本次安装是在G:\Ice
      第三步，设置Ice的环境变量：
主要有环境变量需要设置：path、classpath、ICE_HOME
例如：
path:%ICE_HOME%\bin;
classpath:%ICE_HOME%\Ice.jar;%ICE_HOME%\lib\db.jar;
ICE_HOME:G:\Ice
      第四步，检验：
在命令行中输入：icegridnode --version 
如果现实当前安装的ice版本号，那么就说明安装成功。

使用ice写一个脚本
module demo { 
interface Printer {
void printString(string s);  
}; 
}; 
文件名为printer.ice(可用记事本写，注意脚本的扩展名，将.txt去掉)

