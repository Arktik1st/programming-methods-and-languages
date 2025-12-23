#include <iostream>
#include <string>
#include <cmath>
#include <stdexcept>
using namespace std;

template <typename T>
class LinkedList {
private:
    struct Node {
        T data;
        Node* next;
        Node(const T& value) : data(value), next(nullptr) {}
    };
    
    Node* head;
    size_t size;
    
public:
    LinkedList() : head(nullptr), size(0) {}
    ~LinkedList() {clear();}
    LinkedList(const LinkedList& other) : head(nullptr), size(0) {
        Node* current = other.head;
        while (current) {
            add(current->data);
            current = current->next;
        }
    }
    
    LinkedList& operator=(const LinkedList& other) {
        if (this != &other) {
            clear();
            Node* current = other.head;
            while (current) {
                add(current->data);
                current = current->next;
            }
        }
        return *this;
    }
    
    //добавление элемента в конец списка
    void add(const T& value) {
        Node* newNode = new Node(value);
        if (!head) {
            head = newNode;
        } else {
            Node* current = head;
            while (current->next) {
                current = current->next;
            }
            current->next = newNode;
        }
        size++;
    }
    
    //удаление элемента по индексу
    void removeAt(size_t index) {
        if (index >= size) {
            throw out_of_range("Индекс вне зоны");
        }
        
        if (index == 0) {
            Node* temp = head;
            head = head->next;
            delete temp;
        } else {
            Node* current = head;
            for (size_t i = 0; i < index - 1; i++) {
                current = current->next;
            }
            Node* temp = current->next;
            current->next = temp->next;
            delete temp;
        }
        size--;
    }
    
    //получение элемента по индексу
    T& get(size_t index) {
        if (index >= size) {
            throw out_of_range("Индекс вне зоны");
        }
        
        Node* current = head;
        for (size_t i = 0; i < index; i++) {
            current = current->next;
        }
        return current->data;
    }
    
    //константная версия получения элемента по индексу
    const T& get(size_t index) const {
        if (index >= size) {
            throw out_of_range("Индекс вне зоны");
        }
        
        Node* current = head;
        for (size_t i = 0; i < index; i++) {
            current = current->next;
        }
        return current->data;
    }
    
    //разворот списка
    void reverse() {
        Node* prev = nullptr;
        Node* current = head;
        Node* next = nullptr;
        
        while (current) {
            next = current->next;
            current->next = prev;
            prev = current;
            current = next;
        }
        head = prev;
    }
    
    //преобразование в строку (общая версия)
    string toString() const {
        string result = "[";
        Node* current = head;
        while (current) {
            // Используем оператор << для преобразования в строку
            // Для работы с разными типами создаем временный stringstream
            // Но так как нельзя использовать sstream, сделаем иначе
            
            // Для числовых типов используем to_string
            if constexpr (is_arithmetic_v<T>) {
                result += to_string(current->data);
            } else {
                // Для других типов просто выводим как есть
                // В реальном коде здесь нужно было бы использовать stringstream
                result += "element";  // Заглушка
            }
            
            if (current->next) {
                result += ", ";
            }
            current = current->next;
        }
        result += "]";
        return result;
    }
    
    // Очистка списка
    void clear() {
        Node* current = head;
        while (current) {
            Node* next = current->next;
            delete current;
            current = next;
        }
        head = nullptr;
        size = 0;
    }
    
    // Получение размера списка
    size_t getSize() const {
        return size;
    }
    
    // Проверка на пустоту
    bool isEmpty() const {
        return size == 0;
    }
    
    // Перегрузка оператора []
    T& operator[](size_t index) {
        return get(index);
    }
    
    // Константная версия оператора []
    const T& operator[](size_t index) const {
        return get(index);
    }
};

// Перегрузка оператора вывода для LinkedList<T>
template<typename T>
ostream& operator<<(ostream& os, const LinkedList<T>& list) {
    os << "[";
    if (list.getSize() > 0) {
        for (size_t i = 0; i < list.getSize(); i++) {
            os << list.get(i);
            if (i < list.getSize() - 1) {
                os << ", ";
            }
        }
    }
    os << "]";
    return os;
}

// Перегрузка оператора ввода для LinkedList<T>
template<typename T>
istream& operator>>(istream& is, LinkedList<T>& list) {
    T value;
    while (is >> value) {
        list.add(value);
    }
    // Сброс флага ошибки потока
    is.clear();
    return is;
}

// Пример использования
int main() {
    // Тестирование с целыми числами
    LinkedList<int> intList;
    
    // Добавление элементов
    intList.add(1);
    intList.add(2);
    intList.add(3);
    intList.add(4);
    intList.add(5);
    
    cout << "Исходный список: " << intList << endl;
    cout << "Размер списка: " << intList.getSize() << endl;
    cout << "Элемент по индексу 2: " << intList.get(2) << endl;
    
    // Разворот списка
    intList.reverse();
    cout << "Развернутый список: " << intList << endl;
    
    // Удаление элемента
    intList.removeAt(1);
    cout << "После удаления элемента с индексом 1: " << intList << endl;
    
    // Использование оператора []
    cout << "Элемент по индексу 0: " << intList[0] << endl;
    
    // Тестирование с числами с плавающей точкой
    LinkedList<double> doubleList;
    doubleList.add(1.1);
    doubleList.add(2.2);
    doubleList.add(3.3);
    cout << "\nСписок double: " << doubleList << endl;
    
    // Тестирование копирования
    LinkedList<int> copiedList = intList;
    cout << "Скопированный список: " << copiedList << endl;
    
    // Тестирование очистки
    intList.clear();
    cout << "После очистки, размер: " << intList.getSize() << endl;
    
    // Тестирование ввода из потока
    LinkedList<int> inputList;
    cout << "\nВведите несколько чисел (завершите ввод Ctrl+D/Ctrl+Z): ";
    
    cin >> inputList;
    cout << "Список из ввода: " << inputList << endl;
    return 0;
}
