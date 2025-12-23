#include <iostream>
#include <string>
#include <cmath>
#include <utility>
#include <sstream>
using namespace std;

template <typename T>
class DecartTree {
private:
    struct Node {
        T key;
        int priority;
        Node* left;
        Node* right;
        
        Node(const T& k, int prio) : key(k), priority(prio), left(nullptr), right(nullptr) {}
    };
    
    Node* root;
    
    // Генерация случайного приоритета
    int generatePriority() const {
        static int seed = 12345;
        seed = (seed * 1103515245 + 12345) & 0x7fffffff;
        return seed;
    }
    
    // Разделение дерева по ключу
    pair<Node*, Node*> split(Node* node, const T& key) {
        if (!node) {
            return {nullptr, nullptr};
        }
        
        if (node->key < key) {
            auto result = split(node->right, key);
            node->right = result.first;
            return {node, result.second};
        } else {
            auto result = split(node->left, key);
            node->left = result.second;
            return {result.first, node};
        }
    }
    
    // Слияние двух деревьев
    Node* merge(Node* left, Node* right) {
        if (!left) return right;
        if (!right) return left;
        
        if (left->priority > right->priority) {
            left->right = merge(left->right, right);
            return left;
        } else {
            right->left = merge(left, right->left);
            return right;
        }
    }
    
    // Рекурсивное добавление
    Node* insert(Node* node, const T& key, int priority) {
        if (!node) {
            return new Node(key, priority);
        }
        
        if (priority > node->priority) {
            auto result = split(node, key);
            Node* new_node = new Node(key, priority);
            new_node->left = result.first;
            new_node->right = result.second;
            return new_node;
        }
        
        if (key < node->key) {
            node->left = insert(node->left, key, priority);
        } else if (key > node->key) {
            node->right = insert(node->right, key, priority);
        }
        // Если ключ уже существует, не добавляем повторно
        return node;
    }
    
    // Рекурсивное удаление
    Node* remove(Node* node, const T& key) {
        if (!node) {
            return nullptr;
        }
        
        if (key < node->key) {
            node->left = remove(node->left, key);
        } else if (key > node->key) {
            node->right = remove(node->right, key);
        } else {
            // Нашли узел для удаления
            Node* result = merge(node->left, node->right);
            delete node;
            return result;
        }
        return node;
    }
    
    // Рекурсивный поиск
    Node* find(Node* node, const T& key) const {
        if (!node) {
            return nullptr;
        }
        
        if (key < node->key) {
            return find(node->left, key);
        } else if (key > node->key) {
            return find(node->right, key);
        } else {
            return node;
        }
    }
    
    // Рекурсивная очистка
    void clear(Node* node) {
        if (node) {
            clear(node->left);
            clear(node->right);
            delete node;
        }
    }
    
    // Рекурсивный обход для строки
    void inOrderToString(Node* node, stringstream& ss) const {
        if (node) {
            inOrderToString(node->left, ss);
            if (ss.tellp() > 0) {
                ss << " ";
            }
            ss << node->key << "(" << node->priority << ")";
            inOrderToString(node->right, ss);
        }
    }
    
    // Рекурсивный вывод в поток
    void printTree(Node* node, ostream& os, int depth = 0) const {
        if (node) {
            printTree(node->right, os, depth + 1);
            for (int i = 0; i < depth; i++) {
                os << "    ";
            }
            os << node->key << " [" << node->priority << "]" << endl;
            printTree(node->left, os, depth + 1);
        }
    }
    
    // Копирование дерева
    Node* copyTree(Node* node) {
        if (!node) {
            return nullptr;
        }
        
        Node* new_node = new Node(node->key, node->priority);
        new_node->left = copyTree(node->left);
        new_node->right = copyTree(node->right);
        return new_node;
    }
    
    // Подсчет высоты дерева
    int height(Node* node) const {
        if (!node) {
            return 0;
        }
        return 1 + max(height(node->left), height(node->right));
    }
    
public:
    // Конструкторы
    DecartTree() : root(nullptr) {}
    
    // Конструктор копирования
    DecartTree(const DecartTree& other) {
        root = copyTree(other.root);
    }
    
    // Оператор присваивания
    DecartTree& operator=(const DecartTree& other) {
        if (this != &other) {
            clear();
            root = copyTree(other.root);
        }
        return *this;
    }
    
    // Деструктор
    ~DecartTree() {
        clear();
    }
    
    // Добавление элемента
    void insert(const T& key) {
        root = insert(root, key, generatePriority());
    }
    
    // Добавление элемента с заданным приоритетом (для тестирования)
    void insert(const T& key, int priority) {
        root = insert(root, key, priority);
    }
    
    // Удаление элемента
    void remove(const T& key) {
        root = remove(root, key);
    }
    
    // Поиск элемента
    bool find(const T& key) const {
        return find(root, key) != nullptr;
    }
    
    // Получение значения элемента
    T get(const T& key) const {
        Node* node = find(root, key);
        if (node) {
            return node->key;
        }
        throw runtime_error("Key not found");
    }
    
    // Проверка на пустоту
    bool isEmpty() const {
        return root == nullptr;
    }
    
    // Очистка дерева
    void clear() {
        clear(root);
        root = nullptr;
    }
    
    // Получение строкового представления (симметричный обход)
    string toString() const {
        stringstream ss;
        ss << "[";
        inOrderToString(root, ss);
        ss << "]";
        return ss.str();
    }
    
    // Высота дерева
    int height() const {
        return height(root);
    }
    
    // Вывод дерева в поток (в виде дерева)
    friend ostream& operator<<(ostream& os, const DecartTree<T>& tree) {
        tree.printTree(tree.root, os);
        return os;
    }
    
    // Чтение из потока
    friend istream& operator>>(istream& is, DecartTree<T>& tree) {
        T value;
        while (is >> value) {
            tree.insert(value);
        }
        is.clear();
        return is;
    }
    
    // Оператор вывода в строку (для удобства)
    string toTreeString() const {
        stringstream ss;
        printTree(root, ss);
        return ss.str();
    }
};

// Пример использования
int main() {
    DecartTree<int> tree;
    
    // Добавление элементов
    cout << "Добавление элементов..." << endl;
    tree.insert(50);
    tree.insert(30);
    tree.insert(70);
    tree.insert(20);
    tree.insert(40);
    tree.insert(60);
    tree.insert(80);
    
    // Вывод дерева
    cout << "Дерево (симметричный обход): " << tree.toString() << endl;
    cout << "Высота дерева: " << tree.height() << endl;
    cout << "\nСтруктура дерева:" << endl;
    cout << tree;
    
    // Поиск элементов
    cout << "\nПоиск элементов:" << endl;
    cout << "Поиск 30: " << (tree.find(30) ? "найден" : "не найден") << endl;
    cout << "Поиск 45: " << (tree.find(45) ? "найден" : "не найден") << endl;
    
    // Удаление элементов
    cout << "\nУдаление элементов..." << endl;
    tree.remove(30);
    tree.remove(70);
    
    cout << "Дерево после удаления: " << tree.toString() << endl;
    cout << "Высота дерева: " << tree.height() << endl;
    cout << "\nСтруктура дерева после удаления:" << endl;
    cout << tree;
    
    // Тестирование копирования
    cout << "\nТестирование копирования..." << endl;
    DecartTree<int> tree2 = tree;
    cout << "Копия дерева: " << tree2.toString() << endl;
    
    // Тестирование ввода из потока
    cout << "\nТестирование ввода из потока..." << endl;
    DecartTree<int> tree3;
    stringstream input("10 25 35 45 55 65 75");
    input >> tree3;
    cout << "Дерево из потока: " << tree3.toString() << endl;
    
    // Очистка
    cout << "\nОчистка дерева..." << endl;
    tree.clear();
    cout << "Дерево после очистки: " << (tree.isEmpty() ? "пустое" : "не пустое") << endl;
    
    return 0;
}
