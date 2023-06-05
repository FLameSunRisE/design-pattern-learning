# design-pattern-learn

- [design-pattern-learn](#design-pattern-learn)
  - [實作項目](#實作項目)
    - [1. File-writer-lab](#1-file-writer-lab)

---

## 實作項目

### [1. File-writer-lab](./doc/lab1-file-writer.md)

- [實作程式位置](./lab/lab1-file-writer/src/main/java/com/flamesunrises/lab/filewriter/DemoMain.java)
- 介紹
  可以使用`CsvWriterStrategyBuilder<T>`該策略物件來寫入資料到 CSV 檔案中。
  通過`CsvWriterStrategyBuilder<T>`來構建使用者需要的 `CsvWriterStrategy<T>` 物件，並進一步設定 CSV 檔案的屬性和資料格式處理策略。

- 使用設計模式
  - 建造者模式 (Builder Pattern):
    `CsvWriterStrategyBuilder<T>` 類別使用建造者模式來建構 `CsvWriterStrategy<T>` 物件。它提供了一組流式介面的方法，用於設定 CSV 檔案的相關屬性，並最終構建出完整的 `CsvWriterStrategy<T>` 物件。
  - 策略模式 (Strategy Pattern):
    在 `CsvWriterStrategy<T>` 類別中，使用了策略模式來實現對資料格式的處理。它接受一個 `ICsvFormatStrategy<T>` 物件作為參數，並使用該策略物件來格式化資料並進行寫入操作。這樣可以輕鬆地切換和擴展不同的資料格式處理策略，以滿足不同的需求。
  - 介面隔離原則 (Interface Segregation Principle):
    `ICsvFormatStrategy<T>` 介面根據單一職責原則進行了隔離，將資料格式處理相關的方法進行了抽象。這樣可以使不同的格式處理策略只需要實現自己需要的方法，而不需要實作不必要的方法。
