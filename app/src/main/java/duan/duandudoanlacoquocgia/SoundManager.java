package duan.duandudoanlacoquocgia;

import android.content.Context;
import android.content.SharedPreferences;
import android.media.MediaPlayer;

public class SoundManager {

    private MediaPlayer bgMusic;
    private MediaPlayer sound;

    private Context context;

    // Cài đặt âm thanh
    private boolean nhacNen;
    private boolean hieuUng;

    private float amLuongNhac;
    private float amLuongHieuUng;

    public SoundManager(Context context) {

        this.context = context;

        // Đọc cài đặt đã lưu
        SharedPreferences preferences =
                context.getSharedPreferences("GAME_SETTINGS", Context.MODE_PRIVATE);

        nhacNen = preferences.getBoolean("NHAC_NEN", true);
        hieuUng = preferences.getBoolean("HIEU_UNG", true);

        int volumeMusic =
                preferences.getInt("AM_LUONG_NHAC", 50);

        int volumeEffect =
                preferences.getInt("AM_LUONG_HIEU_UNG", 100);

        // Chuyển từ 0 - 100 sang 0.0 - 1.0
        amLuongNhac = volumeMusic / 100f;
        amLuongHieuUng = volumeEffect / 100f;
    }

    // =========================
    // NHẠC NỀN
    // =========================

    public void playBackgroundMusic() {

        // Nếu tắt nhạc nền thì không phát
        if (!nhacNen) {
            return;
        }

        if (bgMusic == null) {

            bgMusic = MediaPlayer.create(
                    context,
                    R.raw.bg_music
            );

            if (bgMusic != null) {

                bgMusic.setLooping(true);

                bgMusic.setVolume(
                        amLuongNhac,
                        amLuongNhac
                );

                bgMusic.start();
            }
        }
    }

    // =========================
    // ÂM THANH HIỆU ỨNG
    // =========================

    public void playCorrect() {

        if (hieuUng) {
            playSound(R.raw.correct);
        }
    }

    public void playWrong() {

        if (hieuUng) {
            playSound(R.raw.wrong);
        }
    }

    public void playTimeout() {

        if (hieuUng) {
            playSound(R.raw.timeout);
        }
    }

    public void playClick() {

        if (hieuUng) {
            playSound(R.raw.click);
        }
    }

    public void playWin() {

        if (hieuUng) {
            playSound(R.raw.win);
        }
    }

    // =========================
    // PHÁT HIỆU ỨNG
    // =========================

    private void playSound(int soundId) {

        if (sound != null) {
            sound.release();
            sound = null;
        }

        sound = MediaPlayer.create(
                context,
                soundId
        );

        if (sound != null) {

            sound.setVolume(
                    amLuongHieuUng,
                    amLuongHieuUng
            );

            sound.start();
        }
    }

    // =========================
    // DỪNG NHẠC NỀN
    // =========================

    public void stopBackgroundMusic() {

        if (bgMusic != null) {

            if (bgMusic.isPlaying()) {
                bgMusic.stop();
            }

            bgMusic.release();
            bgMusic = null;
        }
    }

    // =========================
    // GIẢI PHÓNG
    // =========================

    public void release() {

        if (bgMusic != null) {

            if (bgMusic.isPlaying()) {
                bgMusic.stop();
            }

            bgMusic.release();
            bgMusic = null;
        }

        if (sound != null) {

            sound.release();
            sound = null;
        }
    }
}