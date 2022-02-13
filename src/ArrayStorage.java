/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private Resume[] storage = new Resume[10000];
    private int numberResume = 0;


    public void clear() {
        for (int i = 0; i < numberResume; i++) {
            storage[i] = null;
        }
        numberResume = 0;
    }

    public void save(Resume resume) {
        boolean isNewResume = true;
        for (int i = 0; i < numberResume; i++) {
            if (storage[i].uuid == resume.uuid) {
                isNewResume = false;
                break;
            }
        }
        if (isNewResume) {
        storage[numberResume] = resume;
        numberResume++;
        }
    }

    public Resume get(String uuid) {
        Resume rezume = new Resume();
        for (int i = 0; i < numberResume; i++) {
            if (storage[i].uuid == uuid) {
                rezume = storage[i];
                break;
            }
        }
        if (rezume.uuid == null) {
            System.out.println("Resume not found");
        }
        return rezume;
    }

    public void delete(String uuid) {
        boolean isResume = false;
        for (int i = 0; i < numberResume; i++) {
            if (storage[i].uuid == uuid) {
                isResume = true;
            }
            if (isResume) {
                storage[i] = storage[i + 1];
            }
        }
        numberResume--;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        Resume[] filledResume = new Resume[numberResume];
        for (int i = 0; i < numberResume; i++) {
            filledResume[i] = storage[i];
        }
        return filledResume;
    }

    public int size() {
        return numberResume;
    }
}
