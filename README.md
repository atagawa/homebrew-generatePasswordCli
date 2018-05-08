Generate Password  
====

Overview  

ランダムなパスワードを生成する機能を提供します。  

## Description

ローカルで実行する機能です。  
実行するとターミナル上にパスワードとして使用可能なランダムな文字列が表示されます。  

## Usage

1. このリポジトリをローカルにCloneする  
2. プロジェクトのルートフォルダに移動し、以下のコマンドを入力する  
    `javac -d bin src/com/example/*.java`  
3. 以下のコマンドを実行する  
    `java -classpath bin com.example.GeneratePassword`  
    
## Arguments

コマンドライン実行時に以下の引数を持たせることができます。  
* -l パスワード文字列の長さ  
    1~64までの整数が指定可能です。  
    指定しないと8桁のパスワードとなります。  
* -c 許可する英字の種類  
    0:英字を許可しない  
    1:大文字のみ許可する  
    2:小文字のみ許可する  
    3:大文字小文字混在を許可する（デフォルト値）  
* -s 記号の許可  
    true:記号を許可する（デフォルト値）  
    false:記号を許可しない  
    
## Example
    java -classpath bin com.example.GeneratePassword
    java -classpath bin com.example.GeneratePassword -l 12 -c 2 -s false
    java -classpath bin com.example.GeneratePassword -s false
