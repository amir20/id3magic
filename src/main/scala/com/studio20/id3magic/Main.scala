package com.studio20.id3magic

import java.io.File
import org.blinkenlights.jid3.MP3File

/**
 * @author amir.raminfar
 */

object Main {
  def findFiles(f: File, ext: String): Array[File] = {
    f.listFiles.filter(_.getName.endsWith(ext)) ++
    f.listFiles.filter(_.isDirectory).flatMap(findFiles(_, ext))
  }


  def main(args: Array[String]) {
    val songs = findFiles(new File(args(0)), ".mp3")
    songs.foreach((file: File) => {
      println(file)
      //val Array(artist, title) = file.getName.split(" -\\s?")
      val mp3 = new MP3File(file)
      val id3 = mp3.getID3V2Tag
      file.renameTo(new File(file.getParentFile, id3.getArtist + " - " + id3.getTitle + ".mp3"))
      //      id3.setArtist(artist)
      /*id3.setTitle(id3.getTitle.replace(".mp3", ""))
      mp3.setID3Tag(id3)
      mp3.sync()*/
    }
    )
  }
}
