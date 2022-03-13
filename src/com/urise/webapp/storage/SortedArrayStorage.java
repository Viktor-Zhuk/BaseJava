package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;
import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    protected void deleteResume(int index) {
        for (int i = index; i < size; i++) {
            storage[i] = storage[i +1];
        }
    }

    @Override
    public void saveResume(Resume resume) {
        int currentCount = 0;
        for (int i = 0; i < size; i++) {
            if (resume.getUuid().compareTo(storage[i].getUuid()) < 0) {
                currentCount = i;
                break;
            }
        }
        Resume currentResume = new Resume();
        for (int i = currentCount; i <= size; i++) {
            currentResume = storage[i];
            storage[i] = resume;
            resume = currentResume;
        }
    }

    @Override
    protected int getIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }
}
